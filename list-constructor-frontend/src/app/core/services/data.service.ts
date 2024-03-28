import { Injectable } from '@angular/core';
import { UUID } from 'crypto';
import { forkJoin } from 'rxjs';
import { ListItemEntity } from '../models/ListItemModels';
import { ConstructedList, ConstructedListType } from '../models/ListModels';
import { ListItemService } from './list-item.service';
import { ListService } from './list.service';
import { StateService, ViewState } from './state.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private lists: ConstructedList[] = [];
  private listItemMap = new Map<UUID, ListItemEntity[]>();

  constructor(
    listService: ListService,
    listItemService: ListItemService,
    stateService: StateService
  ) {
    forkJoin([
      listService.getAll(),
      listItemService.getAllItems(ConstructedListType.CHECK),
      listItemService.getAllItems(ConstructedListType.DETAIL),
      listItemService.getAllItems(ConstructedListType.RANK)
    ]).subscribe(([lists, checkListItems, detailListItems, rankListItems]) => {
      let listItemIdMap = new Map<UUID, ListItemEntity>();

      checkListItems.forEach((item) => listItemIdMap.set(item.id, item));
      detailListItems.forEach((item) => listItemIdMap.set(item.id, item));
      rankListItems.forEach((item) => listItemIdMap.set(item.id, item));

      lists.forEach((list) => {
        this.lists.push(list);

        this.listItemMap.set(
          list.id,
          list.itemIds.map((itemId) => listItemIdMap.get(itemId)!)
        );
      });

      stateService.updateState(ViewState.Home);
    });
  }

  public addList(list: ConstructedList): number {
    this.lists.push(list);
    return this.lists.length;
  }

  public addListItem(index: number, item: ListItemEntity) {
    this.lists[index].itemIds.push(item.id);
    this.listItemMap.get(this.lists[index].id)!.push(item);
  }

  public addListItems(items: ListItemEntity[]) {
    this.lists[-1].itemIds = items.map((item) => item.id);
    this.listItemMap.set(this.lists[-1].id, items);
  }

  public getLists(): ConstructedList[] {
    return this.lists;
  }

  public getListItems(listId: UUID): ListItemEntity[] {
    return this.listItemMap.get(listId)!;
  }

  public updateList(list: ConstructedList, index: number) {
    this.lists[index] = list;
  }

  public updateListItem(item: ListItemEntity, index: number) {
    this.listItemMap.get(item.listId)![index] = item;
  }

  public updateListOrder(lists: ConstructedList[]): UUID[] {
    this.lists = lists;
    return lists.map((list) => list.id);
  }

  public updateListItemOrder(
    list: ConstructedList,
    index: number,
    items: ListItemEntity[]
  ): ConstructedList {
    this.listItemMap.set(list.id, items);

    let newList = { ...list, itemIds: items.map((item) => item.id) };
    this.updateList(newList, index);
    return newList;
  }

  public deleteList(lists: ConstructedList[], listToDelete: ConstructedList) {
    this.updateListOrder(lists);
    this.listItemMap.delete(listToDelete.id);
  }
}
