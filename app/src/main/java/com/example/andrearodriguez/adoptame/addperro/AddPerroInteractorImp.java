package com.example.andrearodriguez.adoptame.addperro;



/**
 * Created by andrearodriguez on 8/20/16.
 */
public class AddPerroInteractorImp implements AddPerroInteractor{

   AddPerroRepository repository;

   public AddPerroInteractorImp(AddPerroRepository repository) {
      this.repository = repository;
   }

   @Override
   public void excecute(String name, String edad, String path) {
      repository.uploadPhoto(name, edad, path);
   }
}
