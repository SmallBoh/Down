package com.example.threaddown;

import java.util.ArrayList;
import java.util.List;

import com.example.threaddown.adapter.DownThreadAdapter;
import com.example.threaddown.bean.DownThreadEntity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ThreadDownActivity extends Activity {

	private ListView listview;
	private DownThreadAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		// initView();
		listview = (ListView) findViewById(R.id.listview);
		adapter = new DownThreadAdapter(ThreadDownActivity.this);
		adapter.setList(getList());
		listview.setAdapter(adapter);
	}
	
	private List<DownThreadEntity> getList() {
		List<DownThreadEntity> list = new ArrayList<DownThreadEntity>();
		list.add(new DownThreadEntity("http://nchc.dl.sourceforge.net/project/sevenzip/7-Zip/9.20/7z920.exe"));
		list.add(new DownThreadEntity("http://abv.cn/music/光辉岁月.mp3"));
		list.add(new DownThreadEntity("http://abv.cn/music/红豆.mp3"));
		list.add(new DownThreadEntity("http://abv.cn/music/list.php"));
 		list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2014/jiaotong/didi_psnger_test4_93_v361_84.ptada"));
  	 	list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2014/anquan/12-25-360MobileSafe.ptada"));
//   	list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2015/xiuxian/Fish_2_MSG3PART_0205_1.2_3.liqu_300008764409_220012314.ptada"));
//  	list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2015/qipai/KuaiLeZhaNiMei_liqu001.ptada"));
// 		list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2015/qipai/qyw_1.4.3_mm_0205_300008286790_2200126314.ptada"));
// 		list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2014/dongzuo/39_0112_xiongda_liqu_3_cm4he1_hPqdsI_300008139114_2200126314_signed.ptada"));
//		
//		list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2012/qipai/DDZ_V1.8.4_29015_VC40_8139_001.ptada"));
		return list;
	}

//	private EditText path;
//
//	private TextView resultView;
//	private ProgressBar progreessbar;
//
//	private static final int PROCESSING = 1;
//	private static final int FAILURE = -1;
//	private Handler handler = new UIHandler();
//
//	private void initView() {
//		path = (EditText) findViewById(R.id.thread_item_path);
//
//		findViewById(R.id.thread_item_downloadbutton).setOnClickListener(this);
//		findViewById(R.id.thread_item_stopbutton).setOnClickListener(this);
//
//		resultView = (TextView) findViewById(R.id.thread_item_resultView);
//		progreessbar = (ProgressBar) findViewById(R.id.thread_item_progressBar);
//	}
//
//	@Override public void onClick(View v) { // TODO Auto-generated method
//	   stub switch (v.getId()) {
//	   
//	   //开始下载 case R.id.thread_item_downloadbutton: downMusic(); break; //暂停下载
//	   case R.id.thread_item_stopbutton: exit(); break; } }	private final class UIHandler extends Handler {
//		public void handleMessage(Message msg) {
//	   switch (msg.what) { case PROCESSING: // 更新进度
//	   progreessbar.setProgress(msg.getData().getInt("size")); float num =
//	   (float) progreessbar.getProgress() / (float) progreessbar.getMax(); int
//	   result = (int) (num   100); // 计算进度 resultView.setText(result + "%"); if
//	   (progreessbar.getProgress() == progreessbar.getMax()) { // 下载完成
//	   Toast.makeText(getApplicationContext(), "下载完成",
//	   Toast.LENGTH_LONG).show(); } break; case FAILURE: // 下载失败
//	   Toast.makeText(getApplicationContext(), "下载失败",
//	   Toast.LENGTH_LONG).show(); break; default: break; } }
//	}
//
//	private void downMusic() { //
//	   http://abv.cn/music/光辉岁月.mp3，可以换成其他文件下载的链接 String stPpath =
//	   path.getText().toString(); String filename =
//	   stPpath.substring(stPpath.lastIndexOf('/') + 1);
//	   
//	   try { // URL编码（这里是为了将中文进行URL编码） filename = URLEncoder.encode(filename,
//	   "UTF-8"); } catch (UnsupportedEncodingException e) { e.printStackTrace();
//	   }
//	   
//	   stPpath = stPpath.substring(0, stPpath.lastIndexOf("/") + 1) + filename;
//	   if (Environment.getExternalStorageState().equals(
//	   Environment.MEDIA_MOUNTED)) { // 保存路径 File savDir =
//	   Environment.getExternalStorageDirectory(); download(stPpath, savDir); }
//	   else { Toast.makeText(getApplicationContext(), "SDcard error",
//	   Toast.LENGTH_LONG).show(); }
//	   
//	   }
//
//	private DownloadTask task;
//
//	private void exit() {
//		if (task != null)
//			task.exit();
//	}
//
//	private void download(String path, File savDir) {
//		task = new DownloadTask(path, savDir);
//		new Thread(task).start();
//	}
//
//	private final class DownloadTask implements Runnable {
//		private String strPath;
//		private File saveDir;
//		private FileDownloader loader;
//
//		public DownloadTask(String strPath, File saveDir) {
//			this.strPath = strPath;
//			this.saveDir = saveDir;
//		}
//
//		/**
//		 * 退出下载
//		 */
//
//		public void exit() {
//			if (loader != null)
//				loader.exit();
//		}
//
//		DownLoadProgressListener downloadProgressListener = new DownLoadProgressListener() {
//
//			@Override
//			public void onDownLoadSize(int size) {
//				Message msg = new Message();
//				msg.what = PROCESSING;
//				msg.getData().putInt("size", size);
//				handler.sendMessage(msg);
//			}
//		};
//
//		public void run() {
//			try {
//				// 实例化一个文件下载器 loader = new
//				FileDownloader(getApplicationContext(), strPath, saveDir, 3); // 设置进度条最大值
//				progreessbar.setMax(loader.getFileSize());
//				loader.download(downloadProgressListener);
//			} catch (Exception e) {
//				e.printStackTrace();
//				handler.sendMessage(handler.obtainMessage(FAILURE));
//				// 发送一条空消息对象
//			}
//		}
//	}

}
