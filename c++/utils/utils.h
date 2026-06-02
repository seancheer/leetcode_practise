//
// Created by ljt on 2026/7/15.
//

#ifndef HELLOWORLDPROJECT_UTILS_H
#define HELLOWORLDPROJECT_UTILS_H

#include <vector>
/**
 * 打印一个二维vector
 * @param v
 */
void printDoubleVector(std::vector<std::vector<int> > &v) {
    printf("----------------------------------------------------------------\n");
    if (v.empty()) {
        printf("Empty!!!");
    }
    for (int i = 0; i < v.size(); i++) {
        std::vector<int> &arr = v[i];
        printf("[");
        for (int j = 0; j < arr.size(); j++) {
            printf("%d", arr[j]);
            if (j < arr.size() - 1) {
                printf("\t");
            }
        }
        printf("]");
        if (i < v.size() - 1) {
            printf("\t");
        }
    }
    printf("\n");
}

/**
 * 打印一个或者多个vector
 * @param vList 多个vector
 */
void printVector(std::initializer_list<std::vector<int> > vList) {
    printf("----------------------------------------------------------------\n");
    for (auto &v: vList) {
        if (v.empty()) {
            printf("Empty!!!");
        }
        printf("[");
        for (int j = 0; j < v.size(); j++) {
            printf("%d", v[j]);
            if (j < v.size() - 1) {
                printf("\t");
            }
        }
        printf("]\n");
    }
}
#endif //HELLOWORLDPROJECT_UTILS_H
