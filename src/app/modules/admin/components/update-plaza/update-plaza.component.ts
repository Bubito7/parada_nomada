import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { AdminService } from "../../services/admin.service";

@Component({
  selector: "app-update-plaza",
  templateUrl: "./update-plaza.component.html",
  styleUrls: ["./update-plaza.component.scss"]
})
export class UpdatePlazaComponent implements OnInit {
  isSpinning = false;
  plazaId!: number;
  imgChanged: boolean = false;
  selectedFile: any;
  imagePreview!: string | ArrayBuffer | null;
  existingImage: string | null = null;
  updateForm!: FormGroup;
  formChanged: boolean = false;

  listOfPlazas = ["PLAZA 1", "PLAZA 2", "PLAZA 3", "PLAZA 4", "PLAZA 5",
    "PLAZA 6", "PLAZA 7", "PLAZA 8", "PLAZA 9", "PLAZA 10",
    "PLAZA 11", "PLAZA 12", "PLAZA 13", "PLAZA 14", "PLAZA 15"];
  listOfType = ["Autocaravana", "Caravana", "Camper"];

  constructor(
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.plazaId = Number(this.activatedRoute.snapshot.params["id"]);

    this.updateForm = this.fb.group({
      nombre: [null, Validators.required],
      precio: [null, [Validators.required, Validators.min(1)]],
      descripcion: [null, Validators.required],
      electricidad: [false],
      agua: [false],
      tipo: [null, Validators.required]
    });

    this.updateForm.valueChanges.subscribe(() => {
      this.formChanged = true;
    });

    this.getPlazaById();
  }

  getPlazaById() {
    this.isSpinning = true;
    this.adminService.getPlazaById(this.plazaId).subscribe((res) => {
      this.isSpinning = false;
      const plazaDto = res;
      this.existingImage = 'data:image/png;base64,' + res.returnedImage;
      this.updateForm.patchValue(plazaDto);
      this.formChanged = false;
    });
  }

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
      this.imgChanged = true;
      this.existingImage = null; // Ocultamos la imagen actual
      this.previewImage();
    }
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  updatePlaza() {
    if (!this.formChanged && !this.imgChanged) {
      this.message.warning("No hay cambios para actualizar", { nzDuration: 3000 });
      return;
    }

    this.isSpinning = true;
    const formData: FormData = new FormData();

    if (this.imgChanged && this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    formData.append('nombre', this.updateForm.get('nombre')!.value);
    formData.append('precio', this.updateForm.get('precio')!.value);
    formData.append('descripcion', this.updateForm.get('descripcion')!.value);
    formData.append('electricidad', this.updateForm.get('electricidad')!.value);
    formData.append('agua', this.updateForm.get('agua')!.value);
    formData.append('tipo', this.updateForm.get('tipo')!.value);

    this.adminService.updatePlaza(this.plazaId, formData).subscribe(
      (res) => {
        this.isSpinning = false;
        this.message.success("Plaza actualizada con éxito", { nzDuration: 5000 });
        this.router.navigateByUrl("/admin/dashboard");
        this.formChanged = false;
        this.imgChanged = false;
      },
      (error) => {
        this.message.error("Error al actualizar la plaza", { nzDuration: 5000 });
      }
    );
  }
}
