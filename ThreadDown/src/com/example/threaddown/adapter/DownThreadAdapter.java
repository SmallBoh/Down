package com.example.threaddown.adapter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.example.threaddown.R;
import com.example.threaddown.bean.DownThreadEntity;
import com.example.threaddown.util.DownLoadProgressListener;
import com.example.threaddown.util.DownRemove;
import com.example.threaddown.util.FileDownloader;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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
		ViewHolder vher = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.thread_item_down, null);
			vher = new ViewHolder(convertView);
			convertView.setTag(vher);
		} else {
			vher = (ViewHolder) convertView.getTag();
		}

		DownThreadEntity threadEntity = getItem(position);
		exprossValue(vher, threadEntity);
		
		
		DownLoadLinener downLoadLinener = new DownLoadLinener(vher, position,
				threadEntity);

		vher.start.setOnClickListener(downLoadLinener);
		vher.stop.setOnClickListener(downLoadLinener);

		 

		return convertView;
	}

	private static final int PROCESSING = 1;
	private static final int FAILURE = -1;

	private void exprossValue(ViewHolder vher, DownThreadEntity entity) {

		if (entity.getUrl() != null) {
			vher.netPath.setText(entity.getUrl());
		} else {
			vher.netPath.setText("Null Net url");
		}
		if (entity.getProgress() != null) {
			vher.progressBar.setProgress(Integer.valueOf(entity.getProgress()));
		} else {
			vher.progressBar.setProgress(0);
		}
		if (entity.getResultText() != null) {
			vher.resultText.setText(entity.getResultText());
		} else {
			vher.resultText.setText("");
		}
	}

	class ViewHolder {

		EditText netPath;
		TextView resultText;
		ProgressBar progressBar;
		Button start;
		Button stop;
		Handler handlerLinner;

		public Handler getHandlerLinner() {
			return handlerLinner;
		}

		public void setHandlerLinner(Handler handlerLinner) {
			this.handlerLinner = handlerLinner;
		}

		ViewHolder(View convertView) {
			initView(convertView);
		}

		private void initView(View convertView) {
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

		}

		private DownloadTask task;

		public void downMusic(String stPpath) {
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

		private void download(String path, File savDir) {

			task = new DownloadTask(path, savDir, getHandlerLinner(),
					progressBar);
			new Thread(task).start();
		}

		public void exit() {
			if (task != null)
				task.exit();
		}

	}

	DownRemove remove = new DownRemove() {
		@Override
		public void onCompleteListRemove(int position) {
			// TODO Auto-generated method stub
			getList().remove(position);
			notifyDataSetChanged();
		}
	};

	public void addItem(String url) {

		// uh.obtainMessage(NOTIFIT_CHANGE_ADTA, new MessageFormat(url));

	}

	private class DownLoadLinener implements OnClickListener {

		private ViewHolder holder;
		private int position;
		private DownThreadEntity entity;

		private Handler handlerLinner = new Handler() {

			public void handleMessage(Message msg) {
				switch (msg.what) {

				case PROCESSING: // 更新进度
					isDownFinish(remove, msg);
					break;
				case FAILURE: // 下载失败
					Toast.makeText(context, "下载失败", Toast.LENGTH_LONG).show();
					break;
				}

			};

			public void isDownFinish(DownRemove onRemove, Message msg) {

				entity.setProgress(msg.getData().getInt("size") + "");
				holder.progressBar.setProgress(Integer.valueOf(entity
						.getProgress()));
				float num = (float) Integer.valueOf(entity.getProgress())
						/ (float) holder.progressBar.getMax();
				int result = (int) (num * 100); // 计算进度

				entity.setResultText(result + "%");
				holder.resultText.setText(entity.getResultText());
				entity.setLoading(true);
				notifyDataSetChanged();
				if (holder.progressBar.getProgress() == holder.progressBar
						.getMax()) {
					// 下载完成
					onRemove.onCompleteListRemove((Integer) holder.progressBar
							.getTag());
				}
			}
		};

		public DownLoadLinener(ViewHolder holder, int position,
				DownThreadEntity entity) {
			super();
			this.holder = holder;
			this.position = position;
			this.entity = entity;
			holder.setHandlerLinner(handlerLinner);
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.thread_item_downloadbutton:
				holder.downMusic(entity.getUrl());
				holder.progressBar.setTag(position);
				break;

			case R.id.thread_item_stopbutton:
				holder.exit();
				break;
			}
		}
	}

	private final class DownloadTask implements Runnable {

		private String strPath;
		private File saveDir;
		private FileDownloader loader;
		private Handler hand;
		private ProgressBar progressBar;

		public DownloadTask(String strPath, File saveDir, Handler hand,
				ProgressBar progressBar) {
			this.strPath = strPath;
			this.saveDir = saveDir;
			this.hand = hand;
			this.progressBar = progressBar;
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
				hand.sendMessage(msg);
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
				hand.sendMessage(hand.obtainMessage(FAILURE)); // 发送一条空消息对象
			}
		}
	}
}
