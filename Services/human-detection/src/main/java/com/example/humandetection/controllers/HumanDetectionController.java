package com.example.humandetection.controllers;

import com.example.humandetection.models.Frame;
import com.example.humandetection.services.HumanDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/human-detection")
public class HumanDetectionController {

  final
  HumanDetectionService humanDetectionService;

  public HumanDetectionController(HumanDetectionService humanDetectionService) {
    this.humanDetectionService = humanDetectionService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/frame")
  public ResponseEntity<String> createFrameForCheckGCV(@RequestBody Frame frame) throws IOException {
    return new ResponseEntity<>(humanDetectionService.postFrameToGCV(frame), HttpStatus.OK);
  }
}
