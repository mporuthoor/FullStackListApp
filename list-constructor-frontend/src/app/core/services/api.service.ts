import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly BASE_URL = environment.apiUrl;
  private options = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Cache-Control', 'no-cache')
      .set('Pragma', 'no-cache')
      .set('Expires', 'Sat, 01 Jan 2000 00:00:00 GMT')
  };

  constructor(private httpClient: HttpClient) {}

  public post(path: string, body: object): Observable<any> {
    return this.httpClient.post(
      this.BASE_URL + path,
      JSON.stringify(body),
      this.options
    );
  }

  public get(
    path: string,
    params: HttpParams = new HttpParams()
  ): Observable<any> {
    return this.httpClient.get(this.BASE_URL + path, { params });
  }

  public put(path: string, body: object): Observable<any> {
    return this.httpClient.put(
      this.BASE_URL + path,
      JSON.stringify(body),
      this.options
    );
  }

  public delete(path: string): Observable<any> {
    return this.httpClient.delete(this.BASE_URL + path);
  }
}
