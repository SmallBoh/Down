package com.example.threaddown.adapter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.example.threaddown.bean.DownThreadEntity;
import com.example.threaddown.util.DownLoadProgressListener;
import com.example.threaddown.util.FileDownloader;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyHandlerRun {
	/*private static final int PROCESSING = 1;
	private static final int FAILURE = -1;
	private ProgressBar progressBar;
	private TextView resultText;
	
	Handler handler = new UIHandler();
	List<DownThreadEntity> list;


	public List<DownThreadEntity> getList() {
		return list;
	}

	public void setList(List<DownThreadEntity> list) {
		this.list = list;
	}

	private Context context;

	public AsyHandlerRun() {
		super();
	}

	public AsyHandlerRun(Context context) {
		super();
		this.context = context;
	}

	public AsyHandlerRun(ProgressBar progressBar, TextView resultText,
			Context context) {
		super();
		this.progressBar = progressBar;
		this.resultText = resultText;
		this.context = context;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public TextView getResultText() {
		return resultText;
	}

	public void setResultText(TextView resultText) {
		this.resultText = resultText;
	}

	private class UIHandler extends Handler {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PROCESSING: // ���½���
				progressBar.setProgress(msg.getData().getInt("size"));
				float num = (float) progressBar.getProgress()
						/ (float) progressBar.getMax();
				int result = (int) (num * 100); // �������
				resultText.setText(result + "%");
				if (progressBar.getProgress() == progressBar.getMax()) {
					// �������
					Toast.makeText(context, "�������", Toast.LENGTH_LONG).show();
					Log.e("Position Log", "TAgPosition ==="+getProgressBar().getTag());
					getList().remove(getProgressBar().getTag());
					new DownThreadAdapter().notifyDataSetChanged();
				}
				break;
			case FAILURE: // ����ʧ��
				Toast.makeText(context, "����ʧ��", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	}

	public void downMusic(String stPpath) {
		// �ļ����ص�����
		String filename = stPpath.substring(stPpath.lastIndexOf('/') + 1);

		try {
			// URL���루������Ϊ�˽����Ľ���URL���룩
			filename = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		stPpath = stPpath.substring(0, stPpath.lastIndexOf("/") + 1) + filename;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// ����·��
			File savDir = Environment.getExternalStorageDirectory();
			download(stPpath, savDir);
		} else {
			Toast.makeText(context, "SDcard error", Toast.LENGTH_LONG).show();
		}
	}

	public void removeItem(int position) {
		list.remove(position);
	}

	public void addItem(DownThreadEntity entity) {
		list.add(entity);
	}

	private DownloadTask task;

	public void exit() {
		if (task != null)
			task.exit();
	}

	private void download(String path, File savDir) {
		task = new DownloadTask(path, savDir);
		new Thread(task).start();
	}

	private class DownloadTask implements Runnable {
		private String strPath;
		private File saveDir;
		private FileDownloader loader;

		public DownloadTask(String strPath, File saveDir) {
			this.strPath = strPath;
			this.saveDir = saveDir;
		}

		*//**
		 * �˳�����
		 *//*
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
					handler.sendMessage(msg);
				 
			}
		};

		public void run() {
			try {
				// ʵ����һ���ļ�������
				loader = new FileDownloader(context, strPath, saveDir, 3);
				// ���ý��������ֵ
				progressBar.setMax(loader.getFileSize());
				loader.download(downloadProgressListener);
			} catch (Exception e) {
				e.printStackTrace();
				handler.sendMessage(handler.obtainMessage(FAILURE)); // ����һ������Ϣ����
			}
		}
	}*/
}
