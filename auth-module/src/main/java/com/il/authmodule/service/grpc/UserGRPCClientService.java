package com.il.authmodule.service.grpc;

import com.il.authmodule.model.UserProfile;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import user.UserRequest;
import user.UserResponse;
import user.UserServiceGrpc;

@Service
public class UserGRPCClientService {
    private static final Logger log = LoggerFactory.getLogger(
            UserGRPCClientService.class);
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserGRPCClientService(
            @Value("${user.service.address:localhost}") String serverAddress,
            @Value("${user.service.grpc.port:9000}") int serverPort) {

        log.info("Connecting to User Service GRPC service at {}:{}",
                serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress,
                serverPort).usePlaintext().build();

        blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public UserResponse createUser(UserProfile userProfile) {

        UserRequest request = UserRequest.newBuilder()
                .setFirstName(userProfile.getFirstName())
                .setLastName(userProfile.getLastName())
                .setAvatar(userProfile.getAvatar() != null ? userProfile.getAvatar() : "")
                .setIsEmailNotification(userProfile.getIsEmailNotification())
                .setVocabUsageCount(userProfile.getVocabUsageCount())
                .build();

        UserResponse response = blockingStub.createUser(request);
        log.info("Received response from User service via GRPC:\n{}", response);
        return response;
    }
}
