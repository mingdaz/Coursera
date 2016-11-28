for i in `seq -w 1 12`
do
    mkdir week$i
    cd week$i
    for j in `seq -w 1 10`
    do
        for k in `seq -w 1 9`
        do
            url=http://web.eecs.umich.edu/~radev/coursera-slides/nlpintro_co${k}_${i}.${j}_DR_Edit.pdf
            if curl --output /dev/null --silent --head --fail "$url"; then
                echo "URL exists: $url"
                curl -O "$url"
            else
                echo "URL does not exist: $url"
            fi
        done
    done
    cd ..
done
