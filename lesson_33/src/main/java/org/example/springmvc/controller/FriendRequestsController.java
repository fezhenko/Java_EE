package org.example.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.FriendRequestsDto;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserRequestService;
import org.example.springmvc.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/friendRequests")
@RequiredArgsConstructor
public class FriendRequestsController {
    final UserRequestService userRequestService;
    final AuthContext authContext;

    @GetMapping
    protected String getIncomingRequests(Model model) {
        final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
        model.addAttribute("requestedUsers", requestedUsers);
        return "friendRequests";
    }

    @PostMapping(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView approveFriendRequest(FriendRequestsDto friendRequestsDto) {
        userRequestService.approveRequest(friendRequestsDto.getUserId(), authContext.getAuthUserId());
        return new RedirectView("friendRequests");
    }
    //TODO:сделать пост - при аппруе создаю в таблице френдс запись, удаляю запись из реквестс, обновляю страницу
}
