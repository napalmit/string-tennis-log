package it.napalm.stringtennislog.utils;

import it.napalm.stringtennislog.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NMMenuAdapter extends ArrayAdapter<NMMenuItemModel> {


	public NMMenuAdapter(Context context) {
		super(context, 0);
	}

	public void addHeader(int title) {
		add(new NMMenuItemModel(title, -1, true));
	}

	public void addItem(int title, int icon) {
		add(new NMMenuItemModel(title, icon, false));
	}

	public void addItem(NMMenuItemModel itemModel) {
		add(itemModel);
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return getItem(position).isHeader ? 0 : 1;
	}

	@Override
	public boolean isEnabled(int position) {
		return !getItem(position).isHeader;
	}

	public static class ViewHolder {
		public final TextView textHolder;
		public final ImageView imageHolder;

		public ViewHolder(TextView text1, ImageView image1) {
			this.textHolder = text1;
			this.imageHolder = image1;
		}
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		NMMenuItemModel item = getItem(position);
		ViewHolder holder = null;
		View view = convertView;

		if (view == null) {
			int layout = R.layout.nm_menu_row;
			if (item.isHeader)
				layout = R.layout.nm_menu_row_header;

			view = LayoutInflater.from(getContext()).inflate(layout, null);

			TextView text1 = (TextView) view.findViewById(R.id.menurow_title);
			ImageView image1 = (ImageView) view.findViewById(R.id.menurow_icon);
			view.setTag(new ViewHolder(text1, image1));
		}

		if (holder == null && view != null) {
			Object tag = view.getTag();
			if (tag instanceof ViewHolder) {
				holder = (ViewHolder) tag;
			}
		}


	    if(item != null && holder != null)
	    {
	    	if (holder.textHolder != null)
				holder.textHolder.setText(item.title);

	        if (holder.imageHolder != null) {
				if (item.iconRes > 0) {
					holder.imageHolder.setVisibility(View.VISIBLE);
					holder.imageHolder.setImageResource(item.iconRes);
				} else {
					holder.imageHolder.setVisibility(View.GONE);
				}
			}
	    }

	    return view;		
	}

}