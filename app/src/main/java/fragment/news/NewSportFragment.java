package fragment.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import base.BaseFragment;

public class NewSportFragment extends BaseFragment {


	public static NewSportFragment newInstance() {

		NewSportFragment fragemnt_location = new NewSportFragment();

		return fragemnt_location;

	}
	@Override
	public void setUpView(View view) {

	}

	@Override
	public void setUpData() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_new, container, false);
	}
}
