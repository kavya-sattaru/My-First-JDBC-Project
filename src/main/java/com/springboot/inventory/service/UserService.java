package com.springboot.inventory.service;

import java.util.List;
import java.util.Optional;

import javax.naming.directory.InvalidAttributeIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.inventory.dto.ResponseDTO;
import com.springboot.inventory.dto.UserDTO;
import com.springboot.inventory.model.User;
import com.springboot.inventory.repository.UserRepository;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDTO createUser(UserDTO userDTO) {
        logger.info("Start: Creating a user with details: {}", userDTO);
        try {
            // Validation logic
            if (userDTO.getFirstname() != null && !userDTO.getFirstname().isEmpty()
                    && !userDTO.getFirstname().matches("^[a-zA-Z]+$")) {
                throw new InvalidAttributeIdentifierException("First Name will only allow alphabets");
            }
            if (userDTO.getLastname() != null && !userDTO.getLastname().isEmpty()
                    && !userDTO.getLastname().matches("^[a-zA-Z]+$")) {
                throw new InvalidAttributeIdentifierException("Last Name will allow only alphabets");
            }
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()
                    && !userDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new InvalidAttributeIdentifierException("Invalid Email, format should be like ------@-------");
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()
                    && !userDTO.getPassword().matches(
                            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
                throw new InvalidAttributeIdentifierException(
                        "Invalid Password, password should contain \n At least one uppercase letter (A-Z).\n"
                                + "At least one lowercase letter (a-z).\n"
                                + "At least one digit (0-9).\n"
                                + "At least one special character (!@#$%^&*() or similar).\n"
                                + "Minimum 8 characters and maximum 20 characters.");
            }
            if (userDTO.getMobile() != null && !userDTO.getMobile().isEmpty()
                    && !userDTO.getMobile().matches("^[6-9]\\d{9}$")) {
                throw new InvalidAttributeIdentifierException("Invalid Mobile, allow only numbers of 10 digits length");
            }
            if (userDTO.getState() != null && !userDTO.getState().isEmpty()
                    && !userDTO.getState().matches("^[a-zA-Z]+$")) {
                throw new InvalidAttributeIdentifierException("Invalid State, allow only alphabets");
            }
            if (userDTO.getPincode() != null && (userDTO.getPincode() == 0)
                    && !userDTO.getPincode().toString().matches("^[1-9][0-9]{5}$")) {
                throw new InvalidAttributeIdentifierException("Invalid Pincode, allow only numbers");
            }

            // User creation logic
            User user = new User();
            user.setFirstname(userDTO.getFirstname());
            user.setLastname(userDTO.getLastname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setMobile(userDTO.getMobile());
            user.setState(userDTO.getState());
            user.setPincode(userDTO.getPincode());

            User userFromDB = userRepository.save(user);

            UserDTO response = new UserDTO();
            response.setId(userFromDB.getId());
            response.setFirstname(userFromDB.getFirstname());
            response.setLastname(userFromDB.getLastname());
            response.setEmail(userFromDB.getEmail());
            response.setPassword(userFromDB.getPassword());
            response.setMobile(userFromDB.getMobile());
            response.setState(userFromDB.getState());
            response.setPincode(userFromDB.getPincode());

            logger.info("End: User created successfully with ID: {}", userFromDB.getId());
            return new ResponseDTO(ResponseDTO.Status.SUCCESS, response);
        } catch (InvalidAttributeIdentifierException e) {
            logger.error("Error during user creation: {}", e.getMessage());
            return new ResponseDTO(ResponseDTO.Status.FAIL, 422, e.getMessage(), null);
        } catch (Exception e) {
            logger.error("Unexpected error during user creation", e);
        }
        return null;
    }

    public List<User> getUsers() {
        logger.info("Start: Fetching all users");
        try {
            List<User> users = userRepository.findAll();
            logger.info("End: Successfully fetched all users. Total users: {}", users.size());
            return users;
        } catch (Exception e) {
            logger.error("Error while fetching users", e);
        }
        return null;
    }

    public ResponseDTO updateUsers(Integer userId, UserDTO userDTO) {
        logger.info("Start: Updating user with ID: {}", userId);
        
        try {
            if (userDTO.getFirstname() != null && !userDTO.getFirstname().isEmpty()
                    && !userDTO.getFirstname().matches("^[a-zA-Z]+$")) {
                throw new InvalidAttributeIdentifierException("First Name will only allow alphabets");
            }
            if (userDTO.getLastname() != null && !userDTO.getLastname().isEmpty()
                    && !userDTO.getLastname().matches("^[a-zA-Z]+$")) {
                throw new InvalidAttributeIdentifierException("Last Name will allow only alphabets");
            }
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()
                    && !userDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new InvalidAttributeIdentifierException("Invalid Email, format should be like ------@-------");
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()
                    && !userDTO.getPassword().matches(
                            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
                throw new InvalidAttributeIdentifierException(
                        "Invalid Password, password should contain \n At least one uppercase letter (A-Z).\n"
                                + "At least one lowercase letter (a-z).\n"
                                + "At least one digit (0-9).\n"
                                + "At least one special character (!@#$%^&*() or similar).\n"
                                + "Minimum 8 characters and maximum 20 characters.");
            }
            if (userDTO.getMobile() != null && !userDTO.getMobile().isEmpty()
                    && !userDTO.getMobile().matches("^[6-9]\\d{9}$")) {
                throw new InvalidAttributeIdentifierException("Invalid Mobile, allow only numbers of 10 digits length");
            }
            if (userDTO.getState() != null && !userDTO.getState().isEmpty()
                    && !userDTO.getState().matches("^[a-zA-Z]+$")) {
                throw new InvalidAttributeIdentifierException("Invalid State, allow only alphabets");
            }
            if (userDTO.getPincode() != null && (userDTO.getPincode() == 0)
                    && !userDTO.getPincode().toString().matches("^[1-9][0-9]{5}$")) {
                throw new InvalidAttributeIdentifierException("Invalid Pincode, allow only numbers");
            }

            Optional<User> userOptionalFromDB = userRepository.findById(userId);
            
            if (userOptionalFromDB.isEmpty()) {
                logger.warn("User with ID {} not found", userId);
                return null;
            }
            User userFromDB = userOptionalFromDB.get();
            logger.debug("Existing user data: {}", userFromDB);

            if (userDTO.getFirstname() != null && !userDTO.getFirstname().isEmpty()) {
				userFromDB.setFirstname(userDTO.getFirstname());
			}
			if (userDTO.getLastname() != null && !userDTO.getLastname().isEmpty()) {
				userFromDB.setLastname(userDTO.getLastname());
			}
			if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
				userFromDB.setEmail(userDTO.getEmail());
			}
			if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
				userFromDB.setPassword(userDTO.getPassword());
			}
			if (userDTO.getMobile() != null && !userDTO.getMobile().isEmpty()) {
				userFromDB.setMobile(userDTO.getMobile());
			}
			if(userDTO.getState() != null && !userDTO.getState().isEmpty()) {
				userFromDB.setState(userDTO.getState());
			}
			if (userDTO.getPincode() != null && (userDTO.getPincode()==0)) {
				userFromDB.setPincode(userDTO.getPincode());
			}
			
            userRepository.save(userFromDB);
            logger.info("End: Successfully updated user with ID: {}", userId);
            
            UserDTO response = new UserDTO();
            response.setId(userFromDB.getId());
            response.setFirstname(userFromDB.getFirstname());
            response.setLastname(userFromDB.getLastname());
            response.setEmail(userFromDB.getEmail());
            response.setPassword(userFromDB.getPassword());
            response.setMobile(userFromDB.getMobile());
            response.setState(userFromDB.getState());
            response.setPincode(userFromDB.getPincode());
            
            return new ResponseDTO(ResponseDTO.Status.SUCCESS, userDTO);
            
        } catch (Exception e) {
            logger.error("Error while updating user with ID: {}", userId, e);
        }
        return null;
    }

    public ResponseDTO deleteUsers(Integer userId) {
        logger.info("Start: Deleting user with ID: {}", userId);
        try {
            Optional<User> userOptionalFromDB = userRepository.findById(userId);
            if (userOptionalFromDB.isEmpty()) {
                logger.warn("User with ID {} not found for deletion", userId);
                return null;
            }
            userRepository.deleteById(userId);
            logger.info("End: Successfully deleted user with ID: {}", userId);
            return new ResponseDTO(ResponseDTO.Status.SUCCESS, null);
        } catch (Exception e) {
            logger.error("Error while deleting user with ID: {}", userId, e);
        }
        return null;
    }

    public Optional<User> getUser(Integer userId) {
        logger.info("Start: Fetching user with ID: {}", userId);
        try {
            Optional<User> user = userRepository.findById(userId);
            logger.info("End: Fetched user with ID: {}", userId);
            return user;
        } catch (Exception e) {
            logger.error("Error while fetching user with ID: {}", userId, e);
        }
        return null;
    }
}
