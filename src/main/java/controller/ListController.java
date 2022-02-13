package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ListService;

import java.util.List;

@Controller
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }


    @PostMapping(value = "/list")
    public ResponseEntity<Void> create(@RequestBody model.List list) {
        listService.create(list);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> read() {
        final List<model.List> list = listService.readAll();

        return makeResponse(list, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") long id) {
        final model.List list = listService.read(id);

        return makeResponse(list, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/list/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        listService.delete(id);
        return  makeResponse(true, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<Void> addToList(@RequestBody Long productId, @RequestBody Long listId) {
        listService.addToList(productId, listId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ResponseEntity<?> makeResponse(Object data, HttpStatus statusOnFail){
        return data != null
                ? new ResponseEntity<>(data,HttpStatus.OK)
                : new ResponseEntity<>(statusOnFail);
    }

    private ResponseEntity<?> makeResponse(List<model.List> collection, HttpStatus statusOnFail){
        return collection != null && !collection.isEmpty()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(statusOnFail);
    }

}

