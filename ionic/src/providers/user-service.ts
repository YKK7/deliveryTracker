import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
  public API = 'http://localhost:8080';
  public USER_API = this.API + '/users';

  constructor(public http: Http) {}

  getUsers(): Observable<any> {
    return this.http.get(this.USER_API)
      .map((response: Response) => response.json());
  }
}
