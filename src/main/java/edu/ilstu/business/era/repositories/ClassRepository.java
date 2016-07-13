package edu.ilstu.business.era.repositories;

/**
 * Repository interface to retrieve class information
 * 
 * @see ClassRepositoryImpl
 * 
 * @author Basheer
 *
 */
public interface ClassRepository {
	
	/**
	 * Get's the buCode from a refId
	 * 
	 * @see ClassRepositoryImpl#getBuCode(String)
	 * @param refId
	 * @return
	 */
	public String getBuCode(String refId);
}
