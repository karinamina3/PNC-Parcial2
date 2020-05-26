package com.uca.capas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private CategoriaService categoriaService;
    
    @RequestMapping("/index")
    public ModelAndView initMain() {
    	return indexView(false);
    }

    @RequestMapping("/libros")
    public ModelAndView verLibros() {
    	ModelAndView mav = new ModelAndView();
        List<Libro> libros = null;
        
        try {
            libros = libroService.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        mav.addObject("libros", libros);
        mav.setViewName("libros");
        
        return mav;
    }
    
    @RequestMapping("/libro")
    public ModelAndView libro() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("libro", new Libro());
        mav.addObject("categorias", getCategorias());
        mav.setViewName("libro");
        
        return mav;
    }
    
    @RequestMapping("/ingresarLibro")
    public ModelAndView ingresarLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        
        if (result.hasErrors()) {
        	mav.addObject("categorias", getCategorias());
            mav.setViewName("libro");
             
        } else {
        	try {
        		libro.setFechaIngreso(new Date());
	            libroService.save(libro);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        	
        	return indexView(true);
        }
        
        return mav;
    }
    
    @RequestMapping("/categoria")
    public ModelAndView categoria() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("categoria", new Categoria());
        mav.setViewName("categoria");
        
        return mav;
    }

    @RequestMapping("/ingresarCategoria")
    public ModelAndView ingresarCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        
        if (result.hasErrors()) mav.setViewName("categoria");     
        else {
        	try {
	            categoriaService.save(categoria);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        	
        	return indexView(true);
        }
        
        return mav;
    }
    
    private List<Categoria> getCategorias() {
    	List<Categoria> categorias = new ArrayList<>();
        
        try {
            categorias = categoriaService.findAll();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        
        return categorias;
    }
    
    private ModelAndView indexView(boolean alerta) {
    	 ModelAndView mav = new ModelAndView();
         mav.setViewName("index");
         mav.addObject("alerta", alerta);
        
         return mav;
    }

}