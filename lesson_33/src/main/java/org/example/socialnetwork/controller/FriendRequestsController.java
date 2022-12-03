package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dto.FriendRequestsDto;
import org.example.socialnetwork.model.User;
import org.example.socialnetwork.service.UserRequestService;
import org.example.socialnetwork.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/friendRequests")
public class FriendRequestsController {
    final UserRequestService userRequestService;
    final AuthContext authContext;

    @GetMapping
    protected String getIncomingRequests(Model model) {
        final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
        model.addAttribute("requestedUsers", requestedUsers);
        model.addAttribute("friendRequestsDto", new FriendRequestsDto());
        return "friendRequests";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, params = "action=accept")
    protected String approveFriendRequest(
            @ModelAttribute("friendRequestsDto") @Valid final FriendRequestsDto friendRequestsDto, final Model model,
            final BindingResult result) {
        if (!result.hasErrors()) {
            userRequestService.approveRequest(Long.valueOf(friendRequestsDto.getUserId()), authContext.getAuthUserId());
        //TODO:сделать пост - при аппруе создаю в таблице френдс запись
            final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
            model.addAttribute("requestedUsers", requestedUsers);
            return "friendRequests";
        }
        final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
        model.addAttribute("requestedUsers", requestedUsers);
        return "friendRequests";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, params = "action=decline")
    protected String declineFriendRequest(
            @ModelAttribute("friendRequestsDto") @Valid final FriendRequestsDto friendRequestsDto, final Model model,
            final BindingResult result) {
        if (!result.hasErrors()) {
            userRequestService.declineRequest(Long.valueOf(friendRequestsDto.getUserId()), authContext.getAuthUserId());
            final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
            model.addAttribute("requestedUsers", requestedUsers);
            return "friendRequests";
        }
        final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
        model.addAttribute("requestedUsers", requestedUsers);
        return "friendRequests";
    }
}
