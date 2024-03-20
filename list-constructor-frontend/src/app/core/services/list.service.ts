import { Injectable } from '@angular/core';
import { ConstructedList } from '../models/ListModels';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { UUID } from 'crypto';

@Injectable({
  providedIn: 'root'
})
export class ListService {
  private readonly LIST_URL = '/lists';

  constructor(private apiService: ApiService) { }

  public create(list: ConstructedList): Observable<ConstructedList> {
    return this.apiService.post(this.LIST_URL, list);
  }

  public getAll(): Observable<ConstructedList[]> {
    return this.apiService.get(this.LIST_URL + '/all');
  }

  public update(list: ConstructedList): Observable<ConstructedList> {
    return this.apiService.put(this.LIST_URL + '/' + list.id, list);
  }

  public delete(id: UUID): Observable<string> {
    return this.apiService.delete(this.LIST_URL + '/' + id);
  }

}
