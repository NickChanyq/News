package fragment.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import base.BaseFragment2;

public class NewCarFragment extends BaseFragment2 {


	public static NewCarFragment newInstance() {

		NewCarFragment fragemnt_location = new NewCarFragment();

		return fragemnt_location;

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_new, container, false);
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return null;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void setDefaultFragmentTitle(String title) {

	}
}
