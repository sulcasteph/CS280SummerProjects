#include <stddef.h>
#include "srt.h"

static void heapify(void *base, size_t n, size_t i, size_t size, int (*compar)(const void *, const void *)) {
    size_t largest = i;
    size_t left = 2 * i + 1;
    size_t right = 2 * i + 2;
    char *qb = base;

    if (left < n && compar(qb + size * left, qb + size * largest) > 0) {
        largest = left;
    }

    if (right < n && compar(qb + size * right, qb + size * largest) > 0) {
        largest = right;
    }

    if (largest != i) {
        swap(qb + size * i, qb + size * largest, size);
        heapify(base, n, largest, size, compar);
    }
}

void srtheap(void *base, size_t nelem, size_t size, int (*compar)(const void *, const void *)) {
    char *qb = base;

    // Build max heap
    for (int i = (int)(nelem / 2) - 1; i >= 0; --i) {
        heapify(base, nelem, i, size, compar);
    }

    // One by one extract elements
    for (size_t i = nelem - 1; i > 0; --i) {
        swap(qb, qb + size * i, size);
        heapify(base, i, 0, size, compar);
    }
}
