import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup = new FormGroup({});  // Inicializar correctamente
  isSpinning: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group(
      {
        name: [null, [Validators.required, Validators.pattern('^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$')]],  // Aceptar solo letras
        email: [null, [Validators.required, Validators.email]],  // Validar email
        password: [null, [Validators.required, Validators.minLength(6)]],  // Contraseña mínima de 6 caracteres
        confirmPassword: [null, [Validators.required]]
      },
      { validators: SignupComponent.passwordMatchValidator }
    );
  }

  // Validador para comprobar si las contraseñas coinciden
  static passwordMatchValidator(group: AbstractControl): ValidationErrors | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { notMatching: true };
  }

  register(): void {
    if (this.signupForm.valid) {
      this.isSpinning = true;  // Muestra el spinner mientras se procesa el registro
      console.log(this.signupForm.value);
      this.authService.register(this.signupForm.value).subscribe((res) => {
        this.isSpinning = false;  // Detiene el spinner
        if (res.id != null) {
          this.message.success('Registro creado con éxito', { nzDuration: 5000 });
          this.router.navigateByUrl('/login');
        } else {
          this.message.error('Algo salió mal', { nzDuration: 5000 });
        }
      });
    } else {
      this.message.error('Por favor, complete todos los campos correctamente', { nzDuration: 5000 });
    }
  }
}
