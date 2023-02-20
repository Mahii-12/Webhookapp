/**
 * 
 */
package controller;

/**
 * @author Lenovo
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entity.WebHook;
import repository.WebhookRepo;


@RestController
@RequestMapping("/webhooks")
public class WebHookController {
	private final static Logger logger = LoggerFactory.getLogger(WebHookController.class);

	private WebhookRepo webHookRepository;

	@Autowired
	public WebHookController(WebhookRepo webHookRepository) {
		super();
		this.webHookRepository = webHookRepository;
	}
	
	@RequestMapping(method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces=MediaType.TEXT_MARKDOWN_VALUE)
	public ResponseEntity<String> addWebHook(@RequestBody WebHook webHook){
		logger.info("New webhook for " + webHook.getDepartmentName() + " is registered");
		List<WebHook> webhooks = webHookRepository.findByDepartmentNameAndType(
                    webHook.getDepartmentName(),
                    webHook.getType());
		if(webhooks != null && webhooks.contains(webHook)){
			return new ResponseEntity<>("Webhook was exists....", HttpStatus.OK);
		}
		webHookRepository.save(webHook);
		return new ResponseEntity<>("Success!!!!", HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WebHook>> getAllWebHooks(){
		List<WebHook> webhooks = new ArrayList<>();
		webHookRepository.findAll().iterator().forEachRemaining(webhooks::add);
		return new ResponseEntity<List<WebHook>>(webhooks, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, 
           value ="/departments/{departmentName}/types/{type}", 
    produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WebHook> getWebHooksByCompanyNameAndType(
                @PathVariable String companyName, 
                @PathVariable String type){
		List<WebHook> webhooks = webHookRepository.findByDepartmentNameAndType(
                   companyName, type);
		return new ResponseEntity<WebHook>(webhooks.get(0), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
           value ="/comapnies/{companyName}/types/{type}", 
    produces=MediaType.TEXT_MARKDOWN_VALUE)
	public ResponseEntity<String> removeWebHook(
               @PathVariable String departmentName, 
               @PathVariable String type){
		List<WebHook> webhooks = webHookRepository.findByDepartmentNameAndType(
                   departmentName, type);
		if(!webhooks.isEmpty()){
			webHookRepository.delete(webhooks.get(0));
			return new ResponseEntity<>("WebHook was deleted....", HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid", HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value ="/ids/{id}", 
    produces=MediaType.TEXT_MARKDOWN_VALUE)
	public ResponseEntity<String> removeWebHookById(@PathVariable Long id){
		WebHook webhook= WebhookRepo.findOne(id);
		if(webhook != null){
			webHookRepository.delete(webhook);
			return new ResponseEntity<>("WebHook was deleted...", HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid ", HttpStatus.OK);
	}
}

