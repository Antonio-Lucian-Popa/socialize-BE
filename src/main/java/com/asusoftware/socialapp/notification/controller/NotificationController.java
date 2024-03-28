package com.asusoftware.socialapp.notification.controller;

import com.asusoftware.socialapp.notification.model.Notification;
import com.asusoftware.socialapp.notification.model.NotificationType;
import com.asusoftware.socialapp.notification.service.NotificationService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Data
@RestController
@RequestMapping(path = "api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // Endpoint for testing purposes
    /*
    @PostMapping("/notifications/send")
    public ResponseEntity<?> sendNotification(@RequestParam UUID senderUserId, @RequestParam UUID recipientUserId, @RequestParam UUID postId, @RequestParam NotificationType notificationType) {
        Notification notification = notificationService.createNotification(senderUserId, recipientUserId,postId, notificationType);
        return ResponseEntity.ok(notification);
    } */
}
