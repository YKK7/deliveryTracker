import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
  public API = 'http://localhost:8080';
  public USER_API = this.API + '/users';

  constructor(public http: Http) { }

  getUsers(): Observable<any> {
    return this.http.get(this.USER_API)
      .map((response: Response) => response.json());
  }

  create(user: any): Observable<any> {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/", JSON.stringify(user), { headers: headers });
  }

  delete(user: any): Observable<any> {
    let DELETE_THIS = "http://localhost:8080/users/" + user.id;
    return this.http.delete(DELETE_THIS);
  }

  getUser(id: any): Observable<any> {
    return this.http.get("http://localhost:8080/users/" + id).map(response => response.json());
  }

  update(user: any): Observable<any> {
    return this.http.put("http://localhost:8080/users/", user).map(response => response.json());
  }
}
