package com.teame.authservice.security;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teame.authservice.dao.AtflMastUsersDao;
import com.teame.authservice.model.CodeMaster;
import com.teame.authservice.model.CustomerMaster;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, CodeMaster {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AtflMastUsersDao ltMastUsersDao;

	public static Map<String, CustomerMaster> ltMastUsersMap = new HashMap<String, CustomerMaster>();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			CustomerMaster ltMastUser = ltMastUsersDao.getLtMastUsersByMobileNumber(username);

			CustomerMaster ltMastUserToken = ltMastUsersMap.get(username);
			
			String userPassword = ltMastUserToken.getUsername();
			
			ltMastUser.setToken(ltMastUserToken.getToken());

			ltMastUsersMap.put(username, ltMastUser);

			String password = ltMastUser.getPassword();


			CharSequence charSeqOTP = password;
			
			final List<String> roles = new ArrayList<String>();

			if (password.equals(userPassword)) {
				// otp matched ...save token into token
				if (ltMastUser.getStatus().equals("INPROCESS")) {
					try {
						CustomerMaster ltMastUserInprocess = new CustomerMaster();
						ltMastUserInprocess.setCust_id(ltMastUser.getCust_id());
						ltMastUserInprocess.setUsername(ltMastUser.getUsername());
						ltMastUserInprocess.setStatus(ltMastUser.getStatus());
						ltMastUserInprocess.setLast_update_date(new Date());
						ltMastUserInprocess.setToken(ltMastUser.getToken());
						ltMastUsersDao.saveCustomerMaster(ltMastUserInprocess);
					} catch (Exception e) {
						e.printStackTrace();
					}
					roles.add("PREVERIFIED");
				} else {
					roles.add(ltMastUser.getUser_type());
					ltMastUsersDao.saveCustomerMaster(ltMastUser);
				}
			}

			final List<AppUser> users = Arrays.asList(new AppUser(ltMastUser.getCust_id(), ltMastUser.getUsername(),
					encoder.encode(charSeqOTP), roles));

			for (AppUser appUser : users) {
				if (appUser.getUsername().equals(username)) {

					List<GrantedAuthority> grantedAuthorities = AuthorityUtils
							.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRoles().get(0));

					return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
				}
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		return null;

	}

	// A (temporary) class represent the user saved in the database.
	private static class AppUser {
		private Long id;
		private String username, password;
		private List<String> roles = new ArrayList<String>();

		public AppUser(Long id, String username, String password, List<String> roles) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.roles = roles;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}

	}
}