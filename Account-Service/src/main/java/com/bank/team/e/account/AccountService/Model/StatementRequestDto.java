package com.bank.team.e.account.AccountService.Model;

import java.util.Date;

public class StatementRequestDto {

	
	String AccNumber;
	String sort;
	String sortOrder;
	Long limit;
	Long offset;
	Date startDate;
	Date endDate;
	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAccNumber() {
		return AccNumber;
	}

	public void setAccNumber(String accNumber) {
		AccNumber = accNumber;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "StatementRequestDto [startDate=" + startDate + ", endDate=" + endDate + ", AccNumber=" + AccNumber
				+ ", sort=" + sort + ", sortOrder=" + sortOrder + ", limit=" + limit + ", offset=" + offset + "]";
	}


}
