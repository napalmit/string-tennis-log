package it.napalm.stringtennislog.utils;

public class NMMenuItemModel {

	public int title;
	public int iconRes;
	public boolean isHeader;

	public NMMenuItemModel(int title, int iconRes,boolean header) {
		this.title = title;
		this.iconRes = iconRes;
		this.isHeader=header;
	}

	public NMMenuItemModel(int title, int iconRes) {
		this(title,iconRes,false);
	}
}
