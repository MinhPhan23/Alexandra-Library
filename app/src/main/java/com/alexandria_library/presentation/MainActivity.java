package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alexandria_library.R;
import com.alexandria_library.dso.Book;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.logic.SearchServiceException;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.Adapter.AllBookListAdapter;
import com.alexandria_library.presentation.Adapter.FinishedBookAdapter;
import com.alexandria_library.presentation.Adapter.InProgressBookAdapter;
import com.alexandria_library.presentation.Adapter.LibraryBookListAdapter;
import com.alexandria_library.presentation.Adapter.SearchListAdapter;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ArrayList<Book> searchList;
    private boolean grid = true;
    private AllBookListAdapter allBookAdapter;
    private FinishedBookAdapter finishedBookAdapter;
    private InProgressBookAdapter inProgressBookAdapter;
    private LibraryBookListAdapter libraryBookListAdapter;
    private SearchListAdapter searchListAdapter;
    private SideBarService sideBarService;
    private SearchService searchService;
    private Button libraryBtn, allListBtn, finishedBtn, inProgressBtn;
    private Button logOut, categoryBtn, account;
    private FrameLayout expandable;
    private EditText searchInput;
    private RecyclerView recyclerView;
    private View rootView;
    private boolean library, all, inProgress,finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = true; all = false; inProgress = false; finish = false;
        findByID();
        bookDistributor();

        searchList = new ArrayList<>();
        searchService = new SearchService();

        sideBarService = LoginActivity.getSideBarService();

        /*****
         * get root view
         */
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //check where is the touch position
                    if(!isViewInBounds(recyclerView, (int)event.getRawX(), (int)event.getRawY())){
                        toggleRecyclerViewGone();
                    }
                }
                return false;
            }
        });


        /*****
         * allListBtn on click
         */
        allListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = false; all = true; inProgress = false; finish = false;
                bookDistributor();
            }
        });

        /*****
         * inProgressBtn on click
         */
        inProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = false; all = false; inProgress = true; finish = false;
                bookDistributor();
            }
        });

        /*****
         * finishedBtn on click
         */
        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = false; all = false; inProgress = false; finish = true;
                bookDistributor();
            }
        });

        /*****
         * libraryBtn on click
         */
        libraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = true; all = false; inProgress = false; finish = false;
                bookDistributor();
            }
        });

        /*****
         * main page change book category's button
         */
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid = !grid;
                bookDistributor();
            }
        });

        /*****
         * main page account button
         */
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /*****
         * toggle Button
         */
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition((ViewGroup) expandable.getParent());
                if(expandable.getVisibility() == View.GONE){
                    //from gone to visibility
                    expandable.setVisibility(View.VISIBLE);
                }
                else{
                    //from visibility to gone
                    expandable.setVisibility(View.GONE);
                }
            }
        });

        /****
         * Get search input
         */
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //don't need to implement
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //don't need to implement
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    //get search bar input when search bar changed
                    String input = s.toString();
                    Log.e("xiang", "input String: "+ input);
                    searchList = searchService.searchInput(input);
                    if(searchList.size() == 0){
                       toggleRecyclerViewGone();
                    }
                    else{
                        toggleRecyclerViewVisible();
                        SearchBar();
                    }

                }catch(SearchServiceException searchException){
                    searchException.printStackTrace();
                }
            }
        });

        /*****
         * get search bar on focus
         */
        searchInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && searchList.size() > 0){
                    toggleRecyclerViewVisible();
                }
                else{
                    toggleRecyclerViewGone();
                }
            }
        });
    }


    /*****
     * book distributor is work for distribute which book list showing
     */
    private void bookDistributor(){
        if(all){
            AllBookCategory();
        }
        else if(inProgress){
            InProgressBookCategory();
        }
        else if(finish){
            FinishedBookCategory();
        }
        else if(library){
            LibraryBookCategory();
        }
    }

    private void findByID(){
        //Getting root view ID
        rootView = findViewById(android.R.id.content);
        //Getting library button
        libraryBtn = findViewById(R.id.library_btn);

        //Getting All button
        allListBtn = findViewById(R.id.all_btn);

        //Getting Finished button
        finishedBtn = findViewById(R.id.finished);

        //Getting in progress button
        inProgressBtn = findViewById(R.id.in_progress_btn);

        //Getting log out button
        logOut = findViewById(R.id.log_out_btn);

        //Change Book display Category button
        categoryBtn = findViewById(R.id.book_display_category_button);

        //go to Authentication page
        account = findViewById(R.id.account);
        
        expandable = findViewById(R.id.frameLayout);

        //Getting Search Bar input immediately
        searchInput = findViewById(R.id.searchInput);

        //Getting Search bar Output recycler view
        recyclerView = findViewById(R.id.search_bar_recycle);
    }

    private void SearchBar(){

        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);

        searchListAdapter = new SearchListAdapter(searchList, this);
        recyclerView.setAdapter(searchListAdapter);

        searchListAdapter.setRecyclerItemClickListener(new SearchListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("xiang", "onRecyclerItemClick:" +position);
            }
        });
    }

    public void toggleRecyclerViewGone(){
        recyclerView.setVisibility(View.GONE);
    }
    public void toggleRecyclerViewVisible(){
        recyclerView.setVisibility(View.VISIBLE);
    }

    public boolean isViewInBounds(View view, int x, int y){
        int [] location = new int [2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        return(x>viewX && x < (viewWidth+viewX)) && (y>viewY && y<(viewHeight+viewY));
    }


    private void LibraryBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            libraryBookListAdapter = new LibraryBookListAdapter(this);
            recyclerView.setAdapter(libraryBookListAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            libraryBookListAdapter = new LibraryBookListAdapter(this);
            recyclerView.setAdapter(libraryBookListAdapter);
        }

        libraryBookListAdapter.setRecyclerItemClickListener(new LibraryBookListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("xiang", "onRecyclerItemClick:" +position);
            }
        });
    }
    private void AllBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            allBookAdapter = new AllBookListAdapter(this);
            recyclerView.setAdapter(allBookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            allBookAdapter = new AllBookListAdapter(this);
            recyclerView.setAdapter(allBookAdapter);
        }

        allBookAdapter.setRecyclerItemClickListener(new AllBookListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("xiang", "onRecyclerItemClick:" +position);
            }
        });
    }
    
    private void FinishedBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            finishedBookAdapter = new FinishedBookAdapter(this);
            recyclerView.setAdapter(finishedBookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            finishedBookAdapter = new FinishedBookAdapter(this);
            recyclerView.setAdapter(finishedBookAdapter);
        }

        finishedBookAdapter.setRecyclerItemClickListener(new FinishedBookAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("xiang", "onRecyclerItemClick:" +position);
            }
        });
    }
    
    private void InProgressBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            inProgressBookAdapter = new InProgressBookAdapter(this);
            recyclerView.setAdapter(inProgressBookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            inProgressBookAdapter = new InProgressBookAdapter(this);
            recyclerView.setAdapter(inProgressBookAdapter);
        }

        inProgressBookAdapter.setRecyclerItemClickListener(new InProgressBookAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("xiang", "onRecyclerItemClick:" +position);
            }
        });
    }

}