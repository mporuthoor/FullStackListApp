import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly BASE_URL = environment.apiUrl;
  private options = { headers: new HttpHeaders().set('Content-Type', 'application/json') };

  constructor(private httpClient: HttpClient) { }

  public post(path: string, body: object): Observable<any> {
    return this.httpClient.post(this.BASE_URL + path, JSON.stringify(body), this.options);
  }
  
  public get(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.httpClient.get(this.BASE_URL + path, { params });
  }

  public put(path: string, body: object): Observable<any> {
    return this.httpClient.put(this.BASE_URL + path, JSON.stringify(body), this.options);
  }

  public delete(path: string): Observable<any> {
    return this.httpClient.delete(this.BASE_URL + path);
  }
  
}
