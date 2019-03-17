package social.alone.server.event.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import social.alone.server.event.Event;
import social.alone.server.event.EventService;
import social.alone.server.oauth2.user.CurrentUser;
import social.alone.server.user.User;

@Controller
@RequestMapping(value = "/api/events/{id}/users")
@AllArgsConstructor
public class EventUserController {

  @Autowired
  EventService eventService;

  @PostMapping
  public ResponseEntity joinEvent(
          @PathVariable("id") Integer eventId,
          @CurrentUser User user
  ) {
    Event event = eventService.joinEvent(eventId, user.getId());
    return ResponseEntity.ok(event);
  }

  @DeleteMapping
  public ResponseEntity joinEventCancel(
          @PathVariable("id") Integer eventId,
          @CurrentUser User user
  ) {
    Event event = eventService.joinEventCancel(eventId, user.getId());
    return ResponseEntity.ok(event);
  }
}