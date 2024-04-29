package cholog.controller;

import cholog.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/members/{id}")
    public ResponseEntity<Void> getMember(@PathVariable Long id) {
        if (true) {
            throw new NotFoundException("Member not found: id=" + id);
        }

        return ResponseEntity.ok().build();
    }

}
