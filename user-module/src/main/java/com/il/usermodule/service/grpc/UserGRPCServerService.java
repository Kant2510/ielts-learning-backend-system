package com.il.usermodule.service.grpc;

import com.il.usermodule.dto.UserRequestDTO;
import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.mapper.UserMapperV2;
import com.il.usermodule.model.UserProfile;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.il.usermodule.service.UserService;

import user.UserRequest;
import user.UserResponse;
import user.UserServiceGrpc.UserServiceImplBase;

@GrpcService
public class UserGRPCServerService extends UserServiceImplBase {
    private final Logger log;// = LoggerFactory.getLogger(UserGRPCServerService.class);
    private final UserService userService;
    private final UserMapperV2 userMapper;

    public UserGRPCServerService(UserService userService, UserMapperV2 userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.log = LoggerFactory.getLogger(UserGRPCServerService.class);
    }

    @Override
    public void createUser(UserRequest userRequest, StreamObserver<UserResponse> responseObserver) {

        log.info("createUser request received {}", UserRequest.parser());

        // Business logic - e.g save to database, perform calculates etc

        UserProfile newUserProfile = new UserProfile.Builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .avatar(userRequest.getAvatar())//.isEmpty() ? userRequest.getAvatar() : null)
                .isEmailNotification(userRequest.getIsEmailNotification())
                .vocabUsageCount((int) userRequest.getVocabUsageCount())
                .build();
        // log the new user profile creation
//        System.out.println("Creating new user profile: ");
//        System.out.println("First Name: " + newUserProfile.getFirstName());
//        System.out.println("Last Name: " + newUserProfile.getLastName());
//        System.out.println("Avatar: " + newUserProfile.getAvatar());
//        System.out.println("Is Email Notification: " + newUserProfile.getIsEmailNotification());
//        System.out.println("Vocab Usage Count: " + newUserProfile.getVocabUsageCount());
//        if (newUserProfile == null) {
//            log.error("Failed to create new user profile from request: {}", userRequest);
//            responseObserver.onError(new RuntimeException("Invalid user profile data"));
//            return;
//        }
        UserRequestDTO userRequestDTO = userMapper.toReqDto(newUserProfile);
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        if (userResponseDTO == null) {
            log.error("Failed to create user in UserService: {}", userRequestDTO);
            responseObserver.onError(new RuntimeException("User creation failed"));
            return;
        }

        UserResponse response = UserResponse.newBuilder()
                .setId(userResponseDTO.getId())
                .setStatus("SUCCESS")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}