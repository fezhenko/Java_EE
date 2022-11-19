package org.example.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserRequestService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/friendRequests")
@RequiredArgsConstructor
public class IncomingRequestController {
    final UserRequestService userRequestService;
    final AuthContext authContext;

    @GetMapping
    protected String getIncomingRequests(Model model) {
    //TODO: добавить инкоминг реквест в интерсептор
        final List<User> requestedUsers = userRequestService.findNotApprovedUserRequests(authContext.getAuthUserId());
        model.addAttribute("requestedUsers", requestedUsers);
        return "friendRequests";
    }

    //TODO:сделать пост - при аппруе создаю в таблице френдс запись, удаляю запись из реквестс, обновляю страницу
}
