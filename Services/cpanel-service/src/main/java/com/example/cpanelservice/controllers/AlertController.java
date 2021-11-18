package com.example.cpanelservice.controllers;

import com.example.cpanelservice.models.*;
import com.example.cpanelservice.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cpanel/alerts")
public class AlertController {

  final
  AlertService alertService;

  public AlertController(AlertService alertService) {
    this.alertService = alertService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> addPerson(@RequestBody KnownPersonBody knownPersonBody) throws IOException {
    alertService.createNewAlert(knownPersonBody);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value="/{uuid}")
  public ResponseEntity<String> getAlertsByUuid(@PathVariable(value = "uuid") String uuid) throws IOException {
    return new ResponseEntity<>(alertService.getAlertByUuid(uuid), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<String> getPersonsInATimeFrame(@RequestParam Map<String, String> params) throws IOException {
    return new ResponseEntity<>(alertService.getAlertsInAGivenTime(params), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{uuid}")
  public ResponseEntity<Void> deleteAlert(@PathVariable(value="uuid") String uuid) throws IOException {
    alertService.deleteAlert(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
