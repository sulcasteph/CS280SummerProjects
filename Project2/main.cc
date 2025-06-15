#include <stdbool.h>
#include <stdio.h>
#include <string>
#include <fstream>
#include <iostream>


using namespace std;
using std::endl;
using std::ifstream;
using std::ios;
using std::ofstream;
using std::string;

bool A(void);
bool E(void);
bool O(void);
bool P(void);
bool U(void);
bool I(void);
bool C(void);
bool L(void);
bool D(void);

string s;

int main(int argc, char *argv[]){
    ifstream file("input.txt");
    string line;

    if(!file){
        cerr << "Error opening file." << endl;
        return 1;
    }

    while(getline(file, line)){
        s = line;

        if(A() && s.empty()){
            cout << "The string \"" << line << "\" is in the language." << endl;
        }
        else{
            cout << "The string \"" << line << "\" is NOT in the language." << endl;
        }
    }

    return 0;
}

bool A(void){
    if(I()){
        if(!s.empty() && s[0] == '='){
            s = s.substr(1);
            if(E()){
                return true;
            }
        }
    }
    return false;
}

bool E(void){
    if(!P()){
        return false;
    }
    while(O()){
        if(!P()){
            return false;
        }
    }
    return true;
}

bool O(void){
    if(s.length() >= 2 && s[0] == '*' && s[1] == '*'){
        s = s.substr(2);
        return true;
    }
    if(!s.empty() && (s[0] == '+' || s[0] == '-' || s[0] == '*' || s[0] == '/')){
        s = s.substr(1);
        return true;
    }
    return false;
}

bool P(void){
    if(I()){
        return true;
    }
    else if(L()){
        return true;
    }
    else if(U()){
        if(I() || L()){
            return true;
        }
    }
    else if(!s.empty() && s[0] == '('){
        s = s.substr(1);
        if(E()){
            if(!s.empty() && s[0] == ')'){
                s = s.substr(1);
                return true;
            }
        }
    }
    return false;
}

bool U(void){
    if(!s.empty() && (s[0] == '+' || s[0] == '-' || s[0] == '!')){
        s = s.substr(1);
        return true;
    }
    return false;
}

bool I(void){
    if(!C()){
        return false;
    }
    while(C()){}
    return true;
}

bool C(void){
    if(!s.empty() && 'a' <= s[0] && s[0] <= 'z'){
        s = s.substr(1);
        return true;
    }
    return false;
}

bool L(void){
    if(!D()){
        return false;
    }
    while(D()){}
    return true;
}

bool D(void){
    if(!s.empty() && '0' <= s[0] && s[0] <= '9'){
        s = s.substr(1);
        return true;
    }
    return false;
}

