package com.il.usermodule.service.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.service.UserService;

import user.UserRequest;
import user.UserResponse;
import user.UserServiceGrpc.UserServiceImplBase;

@GrpcService
public class UserGRPCServerService extends UserServiceImplBase {
    private final Logger log;// = LoggerFactory.getLogger(UserGRPCServerService.class);
    private final UserService userService;

    public UserGRPCServerService(UserService userService) {
        this.userService = userService;
        this.log = LoggerFactory.getLogger(UserGRPCServerService.class);
    }

    @Override
    public void createUser(UserRequest userRequest, StreamObserver<UserResponse> responseObserver) {

        log.info("createUser request received {}", UserRequest.parser());

        // Business logic - e.g save to database, perform calculates etc

//        UserProfile newUserProfile = new UserProfile.Builder()
//                .firstName(userRequest.getFirstName())
//                .lastName(userRequest.getLastName())
//                .avatar(userRequest.getAvatar())//.isEmpty() ? userRequest.getAvatar() : null)
//                .isEmailNotification(userRequest.getIsEmailNotification())
//                .vocabUsageCount((int) userRequest.getVocabUsageCount())
//                .build();
//        UserRequestDTO userRequestDTO = userMapper.toReqDto(newUserProfile);
        UserResponseDTO userResponseDTO = userService.createUser(userRequest);
        if (userResponseDTO == null) {
            responseObserver.onError(new RuntimeException("User creation failed"));
            return;
        }

        UserResponse response = UserResponse.newBuilder()
                .setId(userResponseDTO.id())
                .setStatus("SUCCESS")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}