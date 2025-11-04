package com.fahad.TestProject.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fahad.TestProject.Entity.Product;
import com.fahad.TestProject.R;
import com.fahad.TestProject.dbUtil.ProductDao;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class ProductAdd extends AppCompatActivity {
//
//    private EditText edtName, edtEmail, edtPrice, edtQuantity;
//
//    private Button btnSave;
//
//    private ProductDao productDao;
//
//    private Product product;
//
//    private ImageView imgProduct;
//
//    private Uri selectedImageUri;
//
//    private Uri cameraImageUri;
//
//    private ActivityResultLauncher<Intent> imagePickerLauncher;
//
//    private ActivityResultLauncher<String> permissionLauncher;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_product_add);
//
//        edtName = findViewById(R.id.edtName);
//        edtEmail = findViewById(R.id.edtEmail);
//        edtPrice = findViewById(R.id.edtPrice);
//        edtQuantity = findViewById(R.id.edtQuantity);
//        btnSave = findViewById(R.id.btnSave);
//        imgProduct = findViewById(R.id.imgProduct);
//
//        productDao = new ProductDao(this);
//
//        permissionLauncher = registerForActivityResult(
//                new ActivityResultContracts.RequestPermission(), isGranted -> {
//                    if(isGranted) openCamera();
//                    else Toast.makeText(this,"Camera Permission Denied", Toast.LENGTH_SHORT).show();
//                });
//
//        imagePickerLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if(result.getResultCode() == RESULT_OK && result.getData() != null && result.getData().getData() != null) {
//                        selectedImageUri = result.getData().getData();
//                        imgProduct.setImageURI(selectedImageUri);
//                    } else if (result.getResultCode() == RESULT_OK && cameraImageUri != null) {
//                        selectedImageUri = cameraImageUri;
//                        imgProduct.setImageURI(selectedImageUri);
//                    }
//                });
//
//        // 📸 Image click dialog
//        imgProduct.setOnClickListener(v -> showImageSourceDialog());
//
//        // Check if editing
//        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
//        if (productId != -1) {
//            product = productDao.getProductById(productId);
//            if (product != null) {
//                edtName.setText(product.getName());
//                edtEmail.setText(product.getEmail());
//                edtPrice.setText(String.valueOf(product.getPrice()));
//                edtQuantity.setText(String.valueOf(product.getQuantity()));
//                if (product.getImageUri() != null) {
//                    selectedImageUri = Uri.parse(product.getImageUri());
//                    imgProduct.setImageURI(selectedImageUri);
//                }
//            }
//            btnSave.setOnClickListener(v -> saveProduct(this));
//        }
//
//
//
//        // ✅ Button click listener
////        btnSave.setOnClickListener(v -> {
////
////            // Get user input
////            String name = edtName.getText().toString().trim();
////            String email = edtEmail.getText().toString().trim();
////            String price = edtPrice.getText().toString().trim();
////            String quantity = edtQuantity.getText().toString().trim();
////
////            // Simple validation
////            if (name.isEmpty() || email.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
////                Toast.makeText(ProductAdd.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
////                return;
////            }
////
////            // Create message for dialog
////            String message = "📦 Product Details:\n\n"
////                    + "Name: " + name + "\n"
////                    + "Email: " + email + "\n"
////                    + "Price: " + price + "\n"
////                    + "Quantity: " + quantity;
////
////            // Show popup dialog
////            new AlertDialog.Builder(ProductAdd.this)
////                    .setTitle("Confirm Product Details")
////                    .setMessage(message)
////                    .setIcon(android.R.drawable.ic_dialog_info)
////                    .setPositiveButton("Save", (dialog, which) -> {
////                        Toast.makeText(ProductAdd.this, "Product Saved!", Toast.LENGTH_SHORT).show();
////                    })
////                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
////                    .show();
////        });
////
////        btnSave.setOnClickListener(v -> {
////            saveProduct(this);
////        });
//    }
//
//    private void showImageSourceDialog() {
//        String[] options = {"Camera", "Gallery"};
//        new AlertDialog.Builder(this)
//                .setTitle("Select Image Source")
//                .setItems(options, (dialog, which) -> {
//                    if (which == 0) {
//                        // Camera
//                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                                == PackageManager.PERMISSION_GRANTED) {
//                            openCamera();
//                        } else {
//                            permissionLauncher.launch(Manifest.permission.CAMERA);
//                        }
//                    } else {
//                        // Gallery
//                        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
//                        galleryIntent.setType("image/*");
//                        imagePickerLauncher.launch(galleryIntent);
//                    }
//                })
//                .show();
//    }
//
//    // 📸 Open Camera
//    private void openCamera() {
//        try {
//            File photoFile = createImageFile();
//            cameraImageUri = FileProvider.getUriForFile(
//                    this,
//                    getPackageName() + ".provider",
//                    photoFile
//            );
//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
//            imagePickerLauncher.launch(cameraIntent);
//        } catch (IOException e) {
//            Toast.makeText(this, "Error opening camera", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String fileName = "IMG_" + timeStamp;
//        File storageDir = getExternalFilesDir(null);
//        return File.createTempFile(fileName, ".jpg", storageDir);
//    }
//
//    // 💾 Save Product
//    private void saveProduct(Context con) {
//        String name = edtName.getText().toString().trim();
//        String email = edtEmail.getText().toString().trim();
//        String priceStr = edtPrice.getText().toString().trim();
//        String qtyStr = edtQuantity.getText().toString().trim();
//
//        if (name.isEmpty() || email.isEmpty() || priceStr.isEmpty() || qtyStr.isEmpty()) {
//            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        double price = Double.parseDouble(priceStr);
//        int qty = Integer.parseInt(qtyStr);
//
//        if (product == null) {
//            product = new Product(0, name, email, price, qty,
//                    selectedImageUri != null ? selectedImageUri.toString() : null);
//            long id = productDao.insert(product);
//            if (id > 0) Toast.makeText(this, "Product Added!", Toast.LENGTH_SHORT).show();
//            else Toast.makeText(this, "Insert Failed!", Toast.LENGTH_SHORT).show();
//        } else {
//            product.setName(name);
//            product.setEmail(email);
//            product.setPrice(price);
//            product.setQuantity(qty);
//            product.setImageUri(selectedImageUri != null ? selectedImageUri.toString() : null);
//
//            int rows = productDao.update(product);
//            if (rows > 0) Toast.makeText(this, "Product Updated!", Toast.LENGTH_SHORT).show();
//            else Toast.makeText(this, "Update Failed!", Toast.LENGTH_SHORT).show();
//        }
//
//        Intent intent = new Intent(con, ProductList.class);
//        startActivity(intent);
//        finish();
//    }
//
////    private void saveProduct(){
////        String name = edtName.getText().toString().trim();
////        String email = edtEmail.getText().toString().trim();
////        String priceStr = edtPrice.getText().toString().trim();
////        String qtyStr = edtQuantity.getText().toString().trim();
////
////        if (name.isEmpty() || email.isEmpty() || priceStr.isEmpty() || qtyStr.isEmpty()) {
////            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////        double price = Double.parseDouble(priceStr);
////        int qty = Integer.parseInt(qtyStr);
////
////        if (product == null) {
////            product = new Product(0, name, email, price, qty);
////
////            long id = productDao.insert(product);
////
////            if (id>0) {
////                Toast.makeText(this, "Product Added", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(ProductAdd.this, ProductList.class);
////                startActivity(intent);
////            } else {
////                Toast.makeText(this, "Product Insertion Failed", Toast.LENGTH_SHORT).show();
////            }
////        }
////
////    }
//
//}

public class ProductAdd extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPrice, edtQuantity;
    private Button btnSave;
    private ImageView imgProduct;
    private ProductDao productDao;
    private Product product;
    private Uri selectedImageUri;
    private Uri cameraImageUri;

    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPrice = findViewById(R.id.edtPrice);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnSave = findViewById(R.id.btnSave);
        imgProduct = findViewById(R.id.imgProduct);

        productDao = new ProductDao(this);

        // Permission launcher
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) openCamera();
                    else Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                });

        // Image picker (for both gallery or camera result)
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() !=
                            null && result.getData().getData() != null) {
                        selectedImageUri = result.getData().getData();
                        imgProduct.setImageURI(selectedImageUri);
                    } else if (result.getResultCode() == RESULT_OK && cameraImageUri != null) {
                        selectedImageUri = cameraImageUri;
                        imgProduct.setImageURI(selectedImageUri);
                    }
                });

        // 📸 Image click dialog
        imgProduct.setOnClickListener(v -> showImageSourceDialog());

        // Check if editing
        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        if (productId != -1) {
            product = productDao.getProductById(productId);
            if (product != null) {
                edtName.setText(product.getName());
                edtEmail.setText(product.getEmail());
                edtPrice.setText(String.valueOf(product.getPrice()));
                edtQuantity.setText(String.valueOf(product.getQuantity()));
                if (product.getImageUri() != null) {
                    selectedImageUri = Uri.parse(product.getImageUri());
                    imgProduct.setImageURI(selectedImageUri);
                }
            }
        }

        btnSave.setOnClickListener(v -> saveProduct(this));
    }

    // 🔘 Dialog to choose Camera or Gallery
    private void showImageSourceDialog() {
        String[] options = {"Camera", "Gallery"};
        new AlertDialog.Builder(this)
                .setTitle("Select Image Source")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        // Camera
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_GRANTED) {
                            openCamera();
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA);
                        }
                    } else {
                        // Gallery
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                        galleryIntent.setType("image/*");
                        imagePickerLauncher.launch(galleryIntent);
                    }
                })
                .show();
    }

    // 📸 Open Camera
    private void openCamera() {
        try {
            File photoFile = createImageFile();
            cameraImageUri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".provider",
                    photoFile
            );
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
            imagePickerLauncher.launch(cameraIntent);
        } catch (IOException e) {
            Toast.makeText(this, "Error opening camera", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "IMG_" + timeStamp;
        File storageDir = getExternalFilesDir(null);
        return File.createTempFile(fileName, ".jpg", storageDir);
    }

    // 💾 Save Product
    private void saveProduct(Context con) {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String priceStr = edtPrice.getText().toString().trim();
        String qtyStr = edtQuantity.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || priceStr.isEmpty() || qtyStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int qty = Integer.parseInt(qtyStr);

        if (product == null) {
            product = new Product(0, name, email, price, qty,
                    selectedImageUri != null ? selectedImageUri.toString() : null);

            long id = productDao.insert(product);

            if (id > 0) Toast.makeText(this, "Product Added!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Insert Failed!", Toast.LENGTH_SHORT).show();
        } else {
            product.setName(name);
            product.setEmail(email);
            product.setPrice(price);
            product.setQuantity(qty);
            product.setImageUri(selectedImageUri != null ? selectedImageUri.toString() : null);

            int rows = productDao.update(product);
            if (rows > 0) Toast.makeText(this, "Product Updated!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Update Failed!", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(con, ProductList.class);
        startActivity(intent);
        finish();
    }
}

