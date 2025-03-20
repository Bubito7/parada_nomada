import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  isSpinning: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength]]
    });
  }

  login() {

    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
      if(res.userId != null) {
        const user = {
          id: res.userId,
          role:res.userRole
        }
        StorageService.saveUser(user);
        StorageService.saveToken(res.jwt);
        if(StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("/admin/dashboard");
        }else if(StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl("/customer/dashboard");
        }else {
          this.message.error("las credenciales son incorrectas", { nzDuration: 50000 });
        }
      }
    })

  }
}
