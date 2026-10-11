package org.example.webul.sb_crud_webul.controller;

import org.springframework.ui.Model;
import org.example.webul.sb_crud_webul.dto.MovieRequest;
import org.example.webul.sb_crud_webul.dto.MovieResponse;
import org.example.webul.sb_crud_webul.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/movies")

public class MovieController {
    MovieService movieService;
    MovieController (MovieService movieService){

        this.movieService=movieService;
    }

    @PostMapping
    public String Post(@ModelAttribute MovieRequest request){
        if(request.title()==null||request.title().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"title is empty");
        }else if(request.director()==null||request.director().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "director is empty");
        }else if(request.genre()==null||request.genre().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"genre is empty");
        }
        if(request.year()<0||request.year()>2026){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"year is wrong(input:0~2026)");
        } else if(request.rating()<0||request.rating()>10){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"rating is wrong(input:0.0~10.0)");
        }
        movieService.Create(request);
        return "redirect:/movies";
    }

    @GetMapping
    public String GetAll(Model model){

        model.addAttribute("movies", movieService.findAll());
        return "movies/list";
    }
    @GetMapping("/new")
    public String form(){
        return "movies/form";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MovieResponse GetById(@PathVariable long id){

        return movieService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public MovieResponse Put(@PathVariable long id,@RequestBody MovieRequest request){
        if(request.title()==null||request.title().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"title is empty");
        }else if(request.director()==null||request.director().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "director is empty");
        }else if(request.genre()==null||request.genre().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"genre is empty");
        }
        if(request.year()<0||request.year()>2026){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"year is wrong(input:0~2026)");
        } else if(request.rating()<0||request.rating()>10){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"rating is wrong(input:0.0~10.0)");
        }
        return movieService.Update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void Delete(@PathVariable long id){
        movieService.DeleteById(id);
    }

    @GetMapping("/rating/{minR}")
    @ResponseBody
    public List<MovieResponse> Rating(@PathVariable float minR){
        return movieService.ratingCut(minR);
    }


}
