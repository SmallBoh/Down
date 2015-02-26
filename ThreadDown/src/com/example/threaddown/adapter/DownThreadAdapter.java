package com.example.threaddown.adapter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import com.example.threaddown.R;
import com.example.threaddown.bean.DownThreadEntity;
import com.example.threaddown.util.DownLoadProgressListener;
import com.example.threaddown.util.FileDownloader;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DownThreadAdapter extends BaseAdapter {

	private List<DownThreadEntity> list;
	private Context context;

	private static final int PROCESSING = 1;
	private static final int FAILURE = -1;

	public DownThreadAdapter(Context context) {
		this.context = context;
	}

	public DownThreadAdapter() {
		super();
	}

	public List<DownThreadEntity> getList() {
		return list;
	}

	public void setList(List<DownThreadEntity> list) {
		this.list = list;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return getList().size();
	}

	@Override
	public DownThreadEntity getItem(int position) {
		// TODO Auto-generated method stub
		return getList().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vher;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.thread_item_down, null);
			vher = new ViewHolder(convertView);
			
			convertView.setTag(vher);
		} else {
			vher = (ViewHolder) convertView.getTag();
		}
		vher.setPosition(position);
		exprossValue(vher, getItem(vher.getPosition()));

		return convertView;
	}

	private void exprossValue(ViewHolder vher, DownThreadEntity entity) {
		if (entity.getUrl() != null) {
			vher.netPath.setText(entity.getUrl());
		} else {
			vher.netPath.setText("Null Net url");
		}

	}

	class ViewHolder {
		Handler handler = new UIHandler();
		EditText netPath;
		TextView resultText;
		ProgressBar progressBar;

		Button start;
		Button stop;
		int position;
		
		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			Log.e("Position Log", "setPosition:  ="+ position);
			this.position = position;
		}

		ViewHolder(View convertView) {
			netPath = (EditText) convertView
					.findViewById(R.id.thread_item_path);
			resultText = (TextView) convertView
					.findViewById(R.id.thread_item_resultView);
			progressBar = (ProgressBar) convertView
					.findViewById(R.id.thread_item_progressBar);

			start = (Button) convertView
					.findViewById(R.id.thread_item_downloadbutton);
			stop = (Button) convertView
					.findViewById(R.id.thread_item_stopbutton);
		 
			start.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.e("Position Log", "getPosition:  ="+ position);
					downMusic(getItem(getPosition()).getUrl());
				}
			});
			stop.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					exit();
				}
			});
		}

		private class UIHandler extends Handler {

			public void handleMessage(Message msg) {
				switch (msg.what) {
				case PROCESSING: // 更新进度
					progressBar.setProgress(msg.getData().getInt("size"));
					float num = (float) progressBar.getProgress()
							/ (float) progressBar.getMax();
					int result = (int) (num * 100); // 计算进度
					resultText.setText(result + "%");
					if (progressBar.getProgress() == progressBar.getMax()) {
						// 下载完成
						Toast.makeText(context, "下载完成", Toast.LENGTH_LONG)
								.show();
					//	getList().remove(i);
						notifyDataSetChanged();
					}
					break;
				case FAILURE: // 下载失败
					Toast.makeText(context, "下载失败", Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
			}
		}

		private void downMusic(String stPpath) {
			// 文件下载的链接
			String filename = stPpath.substring(stPpath.lastIndexOf('/') + 1);

			try {
				// URL编码（这里是为了将中文进行URL编码）
				filename = URLEncoder.encode(filename, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			stPpath = stPpath.substring(0, stPpath.lastIndexOf("/") + 1)
					+ filename;
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				// 保存路径
				File savDir = Environment.getExternalStorageDirectory();
				download(stPpath, savDir);
			} else {
				Toast.makeText(context, "SDcard error", Toast.LENGTH_LONG)
						.show();
			}
		}

		private DownloadTask task;

		private void exit() {
			if (task != null)
				task.exit();
		}

		private void download(String path, File savDir) {
			task = new DownloadTask(path, savDir);
			new Thread(task).start();
		}

		private final class DownloadTask implements Runnable {
			private String strPath;
			private File saveDir;
			private FileDownloader loader;

			public DownloadTask(String strPath, File saveDir) {
				this.strPath = strPath;
				this.saveDir = saveDir;
			}

			/**
			 * 退出下载
			 */
			public void exit() {
				if (loader != null)
					loader.exit();
			}
			DownLoadProgressListener downloadProgressListener = new DownLoadProgressListener() {
				@Override
				public void onDownLoadSize(int size) {
					Message msg = new Message();
					msg.what = PROCESSING;
					msg.getData().putInt("size", size);
					Log.e("Position Log", "Message size:  ="+ size);
					handler.sendMessage(msg);
				}
			};
			public void run() {
				try {
					// 实例化一个文件下载器
					loader = new FileDownloader(context, strPath, saveDir, 3);
					// 设置进度条最大值
					 progressBar.setMax(loader.getFileSize());
					 loader.download(downloadProgressListener);
				} catch (Exception e) {
					e.printStackTrace();
					handler.sendMessage(handler.obtainMessage(FAILURE)); // 发送一条空消息对象
				}
			}
		}
	}
}
