package com.tst.casper.web;

import java.util.List;

public class CasperBean {
	 private List<String> fullbgSrcArray;
	 private List<String> fullbgPositionArray;
	 private List<String> bgSrcArray;
	 private List<String> bgPositionArray;
	 private int itemWidth;
	 private int itemHeight;
	 private int lineItemCount;
	public List<String> getFullbgSrcArray() {
		return fullbgSrcArray;
	}
	public void setFullbgSrcArray(List<String> fullbgSrcArray) {
		this.fullbgSrcArray = fullbgSrcArray;
	}
	public List<String> getFullbgPositionArray() {
		return fullbgPositionArray;
	}
	public void setFullbgPositionArray(List<String> fullbgPositionArray) {
		this.fullbgPositionArray = fullbgPositionArray;
	}
	public List<String> getBgSrcArray() {
		return bgSrcArray;
	}
	public void setBgSrcArray(List<String> bgSrcArray) {
		this.bgSrcArray = bgSrcArray;
	}
	public List<String> getBgPositionArray() {
		return bgPositionArray;
	}
	public void setBgPositionArray(List<String> bgPositionArray) {
		this.bgPositionArray = bgPositionArray;
	}
	public int getItemWidth() {
		return itemWidth;
	}
	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}
	public int getItemHeight() {
		return itemHeight;
	}
	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}
	public int getLineItemCount() {
		return lineItemCount;
	}
	public void setLineItemCount(int lineItemCount) {
		this.lineItemCount = lineItemCount;
	}
}
