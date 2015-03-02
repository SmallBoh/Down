package com.example.threaddown;

import java.util.ArrayList;
import java.util.List;

import com.example.threaddown.adapter.DownThreadAdapter;
import com.example.threaddown.bean.DownThreadEntity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ThreadDownActivity extends Activity implements OnClickListener {

	private ListView listview;
	private DownThreadAdapter adapter;

	private Button addItem;
	List<DownThreadEntity> list = new ArrayList<DownThreadEntity>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		listview = (ListView) findViewById(R.id.listview);
		addItem = (Button) findViewById(R.id.fragment_button_add);
		adapter = new DownThreadAdapter(ThreadDownActivity.this);
		adapter.setList(list);
		listview.setAdapter(adapter);
		addItem.setOnClickListener(this);
	}

	private List<DownThreadEntity> getList() {
		List<DownThreadEntity> list = new ArrayList<DownThreadEntity>();
		list.add(new DownThreadEntity(
				"http://nchc.dl.sourceforge.net/project/sevenzip/7-Zip/9.20/7z920.exe"));
		list.add(new DownThreadEntity("http://abv.cn/music/π‚ª‘ÀÍ‘¬.mp3"));
		list.add(new DownThreadEntity("http://abv.cn/music/∫Ï∂π.mp3"));
		list.add(new DownThreadEntity("http://abv.cn/music/list.php"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2014/jiaotong/didi_psnger_test4_93_v361_84.ptada"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2014/anquan/12-25-360MobileSafe.ptada"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2015/xiuxian/Fish_2_MSG3PART_0205_1.2_3.liqu_300008764409_220012314.ptada"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2015/qipai/KuaiLeZhaNiMei_liqu001.ptada"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2015/qipai/qyw_1.4.3_mm_0205_300008286790_2200126314.ptada"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2014/dongzuo/39_0112_xiongda_liqu_3_cm4he1_hPqdsI_300008139114_2200126314_signed.ptada"));
		list.add(new DownThreadEntity(
				"http://filelx.liqucn.com/upload/2012/qipai/DDZ_V1.8.4_29015_VC40_8139_001.ptada"));
		return list;
	}

	int urlIndex = 0;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		list.add(new DownThreadEntity("http://filelx.liqucn.com/upload/2012/qipai/DDZ_V1.8.4_29015_VC40_8139_001.ptada"));
		adapter.setList(list);
		adapter.notifyDataSetChanged();
 
	}

}