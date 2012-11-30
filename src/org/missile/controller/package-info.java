/**
 * Controller layer of the application. 
 * <p>
 * The role of this layer is to provide a mechanism so the view can communicate 
 * with the model. The controller is known as the "expert" of the application, 
 * since it maps the events that happen in the view layer with concrete actions 
 * of the model.
 * <p>
 *  In this implementation, we are using an asynchronous MVC where the controller 
 *  is unidirectional, this means that this layer will not process any responses 
 *  from the model nor update anything in the view. Instead we will implement the 
 *  Observer Pattern in a Facade in the model which will receive the view as a subscriber.
 *  
 */
package org.missile.controller;
