import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ListService } from './core/services/list.service';
import { ConstructedList, ConstructedListType } from './core/models/ListModels';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'list-constructor-frontend';

  constructor(listService: ListService) {
    
    //dummy implementation to test api service
    let lists: ConstructedList[];
    listService.getAll().subscribe((body) => {
      lists = body;
      console.log('get all', lists);
    });

    let list = {
      name: "Constructed List Number " + Date.now(),
      description: "Constructed List Number " + Date.now(),
      type: ConstructedListType.CHECK,
      itemIds: []
    } as ConstructedList;
    listService.create(list).pipe(
      switchMap((body) => {
        console.log('create', body);
        let list2 = {...body, description: "update"}
        return listService.update(list2);
      }),
      switchMap((body) => {
        console.log('update', body);
        let listId = body.id!;
        return listService.delete(listId);
      })).subscribe((body) => console.log('delete', body));

  }
  
}
