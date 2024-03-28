import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListItemEntity } from '../models/ListItemModels';
import { ConstructedList } from '../models/ListModels';
import { ApiService } from './api.service';
import { DataService } from './data.service';
import { StateService, ViewState } from './state.service';

@Injectable({
  providedIn: 'root'
})
export class ListService {
  private readonly LIST_URL = '/lists';

  constructor(
    private apiService: ApiService,
    private dataService: DataService,
    private stateService: StateService
  ) {}

  public create(list: Partial<ConstructedList>) {
    this.stateService.updateState(ViewState.SavingList);

    this.apiService.post(this.LIST_URL, list).subscribe((newList) => {
      this.stateService.setIndex(this.dataService.addList(newList) - 1);
      this.stateService.updateCurrentList(newList);
      this.stateService.updateState(ViewState.CreatingListItems);
    });
  }

  public getAll(): Observable<ConstructedList[]> {
    return this.apiService.get(this.LIST_URL + '/all');
  }

  public updateListData(list: ConstructedList, index: number) {
    this.dataService.updateList(list, index);
    this.update(list);
  }

  public updateItemOrder(
    list: ConstructedList,
    index: number,
    items: ListItemEntity[]
  ) {
    this.update(this.dataService.updateListItemOrder(list, index, items));
  }

  public update(list: ConstructedList) {
    this.stateService.updateCurrentList(list);
    this.apiService.put(this.LIST_URL + '/' + list.id, list).subscribe();
  }

  public delete(lists: ConstructedList[], listToDelete: ConstructedList) {
    this.dataService.deleteList(lists, listToDelete);
    this.apiService.delete(this.LIST_URL + '/' + listToDelete.id).subscribe();
  }
}
