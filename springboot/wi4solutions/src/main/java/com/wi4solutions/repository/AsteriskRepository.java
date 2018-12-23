package com.wi4solutions.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import com.wi4solutions.domain.ActiveCall;

/**
* JPA specific extension of {@link org.springframework.data.repository.Repository}.
*
* @author Oliver Gierke
* @author Christoph Strobl
* @author Mark Paluch
*/
@NoRepositoryBean
public interface AsteriskRepository  {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	String findAll();

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
	 */
	List<ActiveCall> findAll(Sort sort);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll(java.lang.Iterable)
	 */
	

}
