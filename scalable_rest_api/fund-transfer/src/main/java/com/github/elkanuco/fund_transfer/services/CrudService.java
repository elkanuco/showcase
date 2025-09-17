package com.github.elkanuco.fund_transfer.services;

import java.util.List;

import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;

public interface CrudService<T, ID> {
	T create(T dto);

	T findById(ID id) throws NoDataFoundMatchingId;

	T update(T dto) throws NoDataFoundMatchingId;

	void delete(ID id);

	List<T> list();
}
