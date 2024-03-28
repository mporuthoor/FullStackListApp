import { Injectable } from '@angular/core';
import { ConstructedList } from '../models/ListModels';
import { ApiService } from './api.service';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root',
})
export class ListOrderService {
  private readonly LIST_ORDER_URL = '/listOrder';

  constructor(
    private apiService: ApiService,
    private dataService: DataService
  ) {}

  public update(lists: ConstructedList[]) {
    let listIds = this.dataService.updateListOrder(lists);
    this.apiService.put(this.LIST_ORDER_URL, listIds).subscribe();
  }
}
