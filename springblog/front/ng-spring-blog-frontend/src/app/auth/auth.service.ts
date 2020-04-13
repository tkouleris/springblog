import { Injectable } from '@angular/core';
import { RegisterPayload } from './register-payload';
import { Observable } from 'rxjs';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { LoginPayload } from './login-payload';
import { JwtAuthResponse } from './jwt-auth-response';
import {map} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = "http://localhost:8080/api/auth/";

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) { }

  register(registerPayload: RegisterPayload): Observable<any>{
    return this.httpClient.post(this.url + "signup",registerPayload);
  }

  login(loginPaylod: LoginPayload): Observable<boolean>
  {
    return this.httpClient.post<JwtAuthResponse>(this.url + "login", loginPaylod).pipe(map(data=>{
      this.localStorageService.store('authenticationToken', data.authenticationToken);
      this.localStorageService.store('username', data.username);
      return true;
    }));
  }

  isAuthenticated():Boolean
  {
    return this.localStorageService.retrieve('username') !=null;
  }
}
