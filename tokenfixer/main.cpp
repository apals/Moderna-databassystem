
// reads every sentence, and make the sentence table    

#include <fstream>
#include <iostream>
#include <string.h>
#include <stdlib.h> 
//g++ main.cpp
// a.out <article id> <filename> 
int main(int argc, char* argv[]) { 
    if(argc < 2 ) {
        std::cout<<"insert <article id> <filename>"<<std::endl;
    }
    //std::cout<<argv[2]<<std::endl;    
    std::string line;
    std::ifstream myfile (argv[2]);
    long sentence_id = 1;
    int article_id = atoi(argv[1]);

    if (myfile.is_open()){
        while ( getline (myfile,line) )
        {
          std::cout <<article_id <<"."<<sentence_id <<"."<<line << '\n';
          sentence_id++;
          if(sentence_id > 1230652) break;
        }
        myfile.close();
    }else 
        std::cout << "Unable to open file"<<std::endl; 

    return 0;
}
