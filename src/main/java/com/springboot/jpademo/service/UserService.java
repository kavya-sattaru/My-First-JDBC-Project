package com.springboot.jpademo.service;

import java.util.List;
import java.util.Optional;

import javax.naming.directory.InvalidAttributeIdentifierException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpademo.dto.ProfileDTO;
import com.springboot.jpademo.dto.ResponseDTO;
import com.springboot.jpademo.dto.UserDTO;
import com.springboot.jpademo.model.Profile;
import com.springboot.jpademo.model.User;
import com.springboot.jpademo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public ResponseDTO createUser(UserDTO userDTO) {
		try {
			
			if (userDTO.getName() != null && !userDTO.getName().isEmpty()
					&& !userDTO.getName().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Name, allow only alphabets");
			}
			if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()
					&& !userDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
				throw new InvalidAttributeIdentifierException("Invalid Email, format should be like ------@-------");
			}
			if (userDTO.getMobile() != null && !userDTO.getMobile().isEmpty()
					&& !userDTO.getMobile().matches("^[6-9]\\d{9}$")) {
				throw new InvalidAttributeIdentifierException("Invalid Mobile, allow only numbers of 10 digits length");
			}
			if (userDTO.getProfileDTO().getAddress() != null && !userDTO.getProfileDTO().getAddress().isEmpty()
					&& !userDTO.getProfileDTO().getAddress().matches("^[a-zA-Z0-9\\s,.-/#]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Address, allow only numericals");
			}
			if (userDTO.getProfileDTO().getPincode() != null && (userDTO.getProfileDTO().getPincode()==0)
					&& !userDTO.getProfileDTO() .getPincode().toString().matches("^[1-9][0-9]{5}$")) {
				throw new InvalidAttributeIdentifierException("Invalid Pincode, allow only numbers of 6 digits length");
			}
			User user=new User();
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			user.setMobile(userDTO.getMobile());
			
			Profile profile=new Profile();
			profile.setAddress(userDTO.getProfileDTO().getAddress());
			profile.setPincode(userDTO.getProfileDTO().getPincode());
			
			user.setProfile(profile);
			User userFromDB = userRepository.save(user);
			
			UserDTO response=new UserDTO();
			response.setId(userFromDB.getId());
			response.setName(userFromDB.getName());
			response.setEmail(userFromDB.getEmail());
			response.setMobile(userFromDB.getMobile());
			
			ProfileDTO result=new ProfileDTO();
			result.setAddress(userFromDB.getProfile().getAddress());
			result.setPincode(userFromDB.getProfile().getPincode());
			
			response.setProfileDTO(result);
			
			return new ResponseDTO(ResponseDTO.Status.SUCCESS,response);
		}
		catch (InvalidAttributeIdentifierException e) {
			return new ResponseDTO(ResponseDTO.Status.FAIL, 422, e.getMessage(), null);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<User> getUsers() {
		try {
			return userRepository.findAll();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public ResponseDTO updateUsers(Integer userId,UserDTO userDTO) {
		try {
			
			if (userDTO.getName() != null && !userDTO.getName().isEmpty()
					&& !userDTO.getName().matches("^[a-zA-Z]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Name, allow only alphabets");
			}
			if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()
					&& !userDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
				throw new InvalidAttributeIdentifierException("Invalid Email, format should be like ------@------");
			}
			if (userDTO.getMobile() != null && !userDTO.getMobile().isEmpty()
					&& !userDTO.getMobile().matches("^[6-9]\\d{9}$")) {
				throw new InvalidAttributeIdentifierException("Invalid Mobile, allow only numbers of 10 digits of length");
			}
			if (userDTO.getProfileDTO().getAddress() != null && !userDTO.getProfileDTO().getAddress().isEmpty()
					&& !userDTO.getProfileDTO().getAddress().matches("^[a-zA-Z0-9\\s,.-/#]+$")) {
				throw new InvalidAttributeIdentifierException("Invalid Address, allow only numericals");
			}
			if (userDTO.getProfileDTO().getPincode() != null && !(userDTO.getProfileDTO().getPincode()==0)
					&& !userDTO.getProfileDTO() .getPincode().toString().matches("^[1-9][0-9]{5}$")) {
				throw new InvalidAttributeIdentifierException("Invalid Pincode, allow only numbers of 6 digit length");
			}
			
			Optional<User> userOptionalFromDB=userRepository.findById(userId);
			if(userOptionalFromDB.isEmpty()) {
				return null;
			}
			User userFromDB= userOptionalFromDB.get();
			if(userDTO.getName()!=null && !userDTO.getName().isEmpty()) {
				userFromDB.setName(userDTO.getName());
			}
			if(userDTO.getMobile()!=null && !userDTO.getMobile().isEmpty()) {
				userFromDB.setMobile(userDTO.getMobile());
			}
			if(userDTO.getProfileDTO()!=null && userDTO.getProfileDTO().getAddress() !=null && !userDTO.getProfileDTO().getAddress().isEmpty()) {
				userFromDB.getProfile().setAddress(userDTO.getProfileDTO().getAddress());
			}
			if(userDTO.getProfileDTO()!=null && userDTO.getProfileDTO().getPincode() !=null && !(userDTO.getProfileDTO().getPincode()==0)) {
				userFromDB.getProfile().setPincode(userDTO.getProfileDTO().getPincode());
			}
			User user = userRepository.save(userFromDB);
			
			UserDTO response=new UserDTO();
			response.setId(userFromDB.getId());
			response.setName(user.getName());
			response.setEmail(user.getEmail());
			response.setMobile(user.getMobile());
			
			ProfileDTO result=new ProfileDTO();
			result.setAddress(userFromDB.getProfile().getAddress());
			result.setPincode(userFromDB.getProfile().getPincode());
			
			response.setProfileDTO(result);
			
			return new ResponseDTO(ResponseDTO.Status.SUCCESS,response);
		}
		catch (InvalidAttributeIdentifierException e) {
			return new ResponseDTO(ResponseDTO.Status.FAIL, 422, e.getMessage(), null);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public ResponseDTO deleteUsers(Integer userId) {
		try {
			Optional<User> userOptionalFromDB=userRepository.findById(userId);
			if(userOptionalFromDB.isEmpty()) {
				return null;
			}
			userRepository.deleteById(userId);
			UserDTO response=new UserDTO();
			return new ResponseDTO(ResponseDTO.Status.SUCCESS,response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
