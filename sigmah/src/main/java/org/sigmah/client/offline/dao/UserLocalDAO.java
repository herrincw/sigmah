/*
 * All Sigmah code is released under the GNU General Public License v3
 * See COPYRIGHT.txt and LICENSE.txt.
 */

package org.sigmah.client.offline.dao;

import com.google.inject.Inject;
import org.sigmah.shared.dao.UserDAO;
import org.sigmah.shared.domain.User;

import javax.persistence.EntityManager;

/**
 * A UserDAO implementation for use off-line.
 * 
 * @author jon
 *
 */
public class UserLocalDAO extends OfflineDAO<User, Integer> implements UserDAO {

	@Inject
	protected UserLocalDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(User entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public User findById(Integer primaryKey) {
		return em.find(User.class, primaryKey);
	}

	@Override
	public boolean doesUserExist(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserByEmail(String email)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByChangePasswordKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}