import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListItemEntity } from '../models/ListItemModels';
import { ConstructedList, ConstructedListType } from '../models/ListModels';
import { ApiService } from './api.service';
import { DataService } from './data.service';
import { StateService, ViewState } from './state.service';

@Injectable({
  providedIn: 'root'
})
export class ListItemService {
  private readonly CHECK_LIST_ITEM_URL = '/checkListItems';
  private readonly DETAIL_LIST_ITEM_URL = '/detailListItems';
  private readonly RANK_LIST_ITEM_URL = '/rankListItems';

  constructor(
    private apiService: ApiService,
    private dataService: DataService,
    private stateService: StateService
  ) {}

  private getUrl(type: ConstructedListType): string {
    if (type == ConstructedListType.CHECK) {
      return this.CHECK_LIST_ITEM_URL;
    } else if (type == ConstructedListType.DETAIL) {
      return this.DETAIL_LIST_ITEM_URL;
    } else {
      return this.RANK_LIST_ITEM_URL;
    }
  }

  public createItem(
    type: ConstructedListType,
    index: number,
    item: Partial<ListItemEntity>
  ) {
    this.apiService.post(this.getUrl(type), item).subscribe((createdItem) => {
      this.dataService.addListItem(index, createdItem);
    });
  }

  public createMultipleItems(
    type: ConstructedListType,
    items: Partial<ListItemEntity>[]
  ) {
    this.stateService.updateState(ViewState.SavingListItems);

    this.apiService
      .post(this.getUrl(type) + '/batch', items)
      .subscribe((createdItems) => {
        this.dataService.addListItems(createdItems);
        this.stateService.updateState(ViewState.Home);
      });
  }

  public getAllItems(type: ConstructedListType): Observable<ListItemEntity[]> {
    return this.apiService.get(this.getUrl(type) + '/all');
  }

  public updateItem(
    type: ConstructedListType,
    item: ListItemEntity,
    index: number
  ) {
    this.dataService.updateListItem(item, index);
    this.apiService.put(this.getUrl(type) + '/' + item.id, item).subscribe();
  }

  public deleteItem(
    type: ConstructedListType,
    list: ConstructedList,
    index: number,
    items: ListItemEntity[],
    itemToDelete: ListItemEntity
  ) {
    this.dataService.updateListItemOrder(list, index, items);
    return this.apiService.delete(this.getUrl(type) + '/' + itemToDelete.id);
  }
}
